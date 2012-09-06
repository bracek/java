package fi.ixonos.projects.web.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.zkoss.image.AImage;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.servlet.ProjectsInitServlet;

public final class PhotoUtil {

    final static protected UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");

    /**
     * handle uploading photo
     * @param event
     * @throws java.lang.Exception
     */
    public static boolean uploadPhoto(Users user, byte[] byteArrayPhoto) throws Exception {
        int max_photo_size;
        try {
            max_photo_size = Integer.parseInt(ProjectsInitServlet.props.getProperty("max_photo_size"));
        } catch (Exception e) {
            max_photo_size = 1500000;
        }
        try {
            if (byteArrayPhoto.length > max_photo_size) {
                Messagebox.show(Labels.getLabel("photo.photoHasBigSize") + " " + max_photo_size / 1000 + " KB", "Error", Messagebox.OK, Messagebox.ERROR);
                return false;
            }
            new AImage("Photo", byteArrayPhoto);
            usersService.updatePhoto(user.getUsername(), byteArrayPhoto);
            Messagebox.show(Labels.getLabel("photo.photoUpdated"), Labels.getLabel("photo.information"), Messagebox.OK, Messagebox.INFORMATION);
            user.setPhoto(byteArrayPhoto);
            user.setPhotoUploaded(true);
            return true;
        } catch (Exception e) {
            Messagebox.show(Labels.getLabel("photo.badPhotoInserted"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
        return false;
    }

    /**
     * handle deleting photo
     * @param event
     * @throws java.lang.Exception
     */
    public static boolean deletePhoto(Users user) throws Exception {
        int answer = Messagebox.show(Labels.getLabel("photo.deletePhotoQuestion"), Labels.getLabel("photo.confirmation"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
        if (answer == Messagebox.YES) {
            usersService.updatePhoto(user.getUsername(), null);
            Messagebox.show(Labels.getLabel("photo.photoDeleted"), Labels.getLabel("photo.information"), Messagebox.OK, Messagebox.INFORMATION);
            user.setPhotoUploaded(false);
            return true;
        }
        return false;
    }

    /**
     * handle refreshing photo
     * @param event
     * @throws java.lang.Exception
     */
    public static void refreshPhoto(Users user, Image usersPhoto, Component deletePhotoButton, boolean normalSize) throws Exception {
        int max_photo_width;
        int max_photo_height;
        try {
            max_photo_width = Integer.parseInt(ProjectsInitServlet.props.getProperty("max_photo_width"));
            max_photo_height = Integer.parseInt(ProjectsInitServlet.props.getProperty("max_photo_height"));
        } catch (Exception e) {
            max_photo_width = 150;
            max_photo_height = 125;
        }
        if (user.isPhotoUploaded()) {
            try {
                if (normalSize) {
                    usersPhoto.setContent(new AImage("Photo", user.getPhoto()));
                } else {
                    usersPhoto.setContent(new AImage("Photo", createThumbnailImage(user.getPhoto(), max_photo_width, max_photo_height)));
                }
                if (deletePhotoButton != null) {
                    deletePhotoButton.setVisible(true);
                }
            } catch (Exception e) {
                usersPhoto.setSrc(ProjectsInitServlet.props.getProperty("default_photo"));
                if (deletePhotoButton != null) {
                    deletePhotoButton.setVisible(false);
                }
            }
        } else {
            usersPhoto.setSrc(ProjectsInitServlet.props.getProperty("default_photo"));
            if (deletePhotoButton != null) {
                deletePhotoButton.setVisible(false);
            }
        }
    }

    private static byte[] createThumbnailImage(byte[] imageData, int maxWidth, int maxHeight) throws IOException {
        InputStream in = new ByteArrayInputStream(imageData);
        BufferedImage image = ImageIO.read(in);
        if (image == null) {
            return null;
        }
        int width = image.getWidth();
        int height = image.getHeight();

        if (maxWidth > 0 && width > maxWidth) {
            double ratio = (double) maxWidth / image.getWidth();
            height = (int) (image.getHeight() * ratio);
            width = maxWidth;
        }

        if (maxHeight > 0 && height > maxHeight) {
            double ratio = (double) maxHeight / height;
            width = (int) (width * ratio);
            height = maxHeight;
        }
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        ByteArrayOutputStream encoderOutputStream = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(encoderOutputStream);
        encoder.encode(bufferedResizedImage);
        return encoderOutputStream.toByteArray();
    }
}
