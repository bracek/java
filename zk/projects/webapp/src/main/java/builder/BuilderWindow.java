package builder;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import org.zkoss.zk.ui.event.Event;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import org.apache.log4j.Logger;
import org.zkoss.util.media.Media;
import org.zkoss.zul.*;

/**
 *
 * @author katrami
 */
public final class BuilderWindow extends Window {

    private String PROJECTS_DIR = "c:\\_tmp\\";
    private String projectName = "fanatik";
    public static final String DOUBLE_BACKSLASH = "\\";
    private String uploadFileName = projectName + "80x80.png";
    private String PROJECT_PATH = PROJECTS_DIR + DOUBLE_BACKSLASH + projectName + DOUBLE_BACKSLASH;
    private Image img;
    private Label msgLb;
    private static final int FILE_SIZE = 100;// 100k
    private static final Logger logger = Logger.getLogger(BuilderWindow.class);
    private String apiFilePath = PROJECT_PATH + "js" + DOUBLE_BACKSLASH + "API.js";
    private Hashtable fileContentHashTable = new Hashtable();

    public BuilderWindow() {
    }
    private Builder builder;
//    private ProjectsService projectsService = (ProjectsService) ProjectsApplicationContext.getApplicationContext().getBean("projectsService");

    public void updateData() {
        logger.info("PROJECT_PATH: " + PROJECT_PATH);
        String[] values;

        try {
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream(apiFilePath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
//                System.out.println(strLine);

                //get this line
                if (strLine.contains("=")) {
                    values = strLine.split("=");
                    String key = values[0];
                    logger.debug("key: " + key);
                    String value = values[1];
                    logger.debug("value: " + value);
                    fileContentHashTable.put(key, value);
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }


        System.out.println("Printing the entire table.");
        Enumeration iterator = fileContentHashTable.elements();
        while (iterator.hasMoreElements()) {
            String temp = (String) iterator.nextElement();
            System.out.println(temp);
        }

    }

    public void saveImage() {
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

    private void saveFile(Media media) {
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
            msgLb.setValue("sucessed upload :" + media.getName());
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

    public void onCancel(final Event event) {
        this.detach();
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
}