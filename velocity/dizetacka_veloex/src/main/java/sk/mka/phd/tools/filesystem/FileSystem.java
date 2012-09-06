package sk.mka.phd.tools.filesystem;

/**
 *
 * @author bracek
 * @date Nov 21, 2009
 */
public class FileSystem {

    public static final String getLoggedUser() {
        String user = null;
        String absPath = System.getProperty("user.dir");
        String folders[] = absPath.split("/");

        int j = 0;
        for (int i = 0; i < folders.length; i++) {
            String string = folders[i];
            if (!string.isEmpty()) {
                System.out.println("string = " + string);
                j++;
                if (j == 2) {
                    user = string;
                    break;
                }
            }
        }
        return user;
    }
}
