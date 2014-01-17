package ctrl;

import builder.Builder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

public class UploadCtrl extends GenericForwardComposer {
    
    
    private Builder builder ;
    
    private String PROJECTS_DIR = "c:\\_tmp\\";
    private String projectName = "fanatik";

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(final Builder builder) {
        this.builder = builder;
    }

    public static final String DOUBLE_BACKSLASH = "\\";
    private String uploadFileName = projectName + "80x80.png";
    
    private String PROJECT_PATH = PROJECTS_DIR + DOUBLE_BACKSLASH + projectName + DOUBLE_BACKSLASH;
    private static final Logger logger = Logger
			.getLogger(UploadCtrl.class);
    
    private Image img;
    private Label msgLb;
    private static final int FILE_SIZE = 100;// 100k

    public UploadCtrl() {
        logger.info("PROJECT_PATH: " + PROJECT_PATH);
    }          
    
    @Override
    public void doAfterCompose(final Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

    public void onClick$uploadBtn() {
        msgLb.setValue("");

        try {
            Media media = Fileupload.get();

            if (media == null) {
                msgLb.setValue("please select a file");
                return;
            }

            String type = media.getContentType().split("/")[0];
            if (type.equals("image")) {
                if (media.getByteData().length > FILE_SIZE * 1024) {
                    msgLb.setValue("File size limit " + FILE_SIZE + "k");
                    return;
                }
                org.zkoss.image.Image picture = (org.zkoss.image.Image) media;
                img.setContent(picture);
            }
            saveFile(media);            

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
     

    private void saveFile(final Media media) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            InputStream fin = media.getStreamData();
            in = new BufferedInputStream(fin);

            File baseDir = new File(PROJECT_PATH);

            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }

//            File file = new File(PROJECT_PATH + media.getName());
            String finalPathName = PROJECT_PATH + uploadFileName;
            File file = new File(finalPathName);

            OutputStream fout = new FileOutputStream(file);
            out = new BufferedOutputStream(fout);
            byte buffer[] = new byte[1024];
            int ch = in.read(buffer);
            while (ch != -1) {
                out.write(buffer, 0, ch);
                ch = in.read(buffer);
            }
            msgLb.setValue("sucessed upload:" + media.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
