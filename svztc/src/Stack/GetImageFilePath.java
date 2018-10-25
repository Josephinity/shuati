package Stack;

import java.util.*;
/**
 * Google
 *
 You are given a listing of directories and files in a file system. Each directory and file has a name, which is a non-empty
 string consisting of alphanumerical characters. Additionally, the name of each file contains a single dot character; the part
 of the name starting with the dot is called the extension. Directory names do not contain any dots.
 Each entry is listed on a separate line. Every directory is followed by the listing of its contents indented by one space
 character. The contents of the root directory are not indented.

 */
public class GetImageFilePath {

    public static void main(String[] args) {
        String s = "usr\n" + " local\n" + "  profile.jpg\n" + " photo\n" + "  img.png\n"
                + "  var\n" + "   tmp\n" + " snapshot.gif\n" + "lib.png\n" + "etc\n"
                + " archive.txt\n" + " module\n" + "  img.png\n";
        System.out.println(s);
        System.out.println(getPaths(s));

    }


    public static List<String> getPaths(String directory) {

        String[] lines = directory.split("\n");
        List<String> paths = new ArrayList<>();
        List<String> stack = new ArrayList<>();

        for(String name: lines) {
            int level = countSpaces(name);
            boolean isFolder = (name.indexOf('.') == -1);
            String fname = name.substring(level, name.length());
            if(isFolder) fname = fname + "/";
            stack.add(level, fname);
            if(!isFolder && isImage(fname)) {
                StringBuilder path = new StringBuilder();
                for(int i = 0; i <= level; i++) {
                    path.append(stack.get(i));
                }
                paths.add(path.toString());
            }
        }
        return paths;
    }

    public static boolean isImage(String f) {
        String extension = f.substring(f.indexOf('.') + 1, f.length());
        if(extension.equals("gif") ||
                extension.equals("jpg") ||
                extension.equals("png") ||
                extension.equals("jpeg")) return true;
        return false;
    }

    private static int countSpaces(String f) {
        int count = 0;
        while(count < f.length() && f.charAt(count) == ' ') {
            count++;
        }
        return count;
    }

}

