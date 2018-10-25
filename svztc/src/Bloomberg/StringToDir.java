package Bloomberg;

/**
 2. parse string into file directory  (Fail)

 "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"

 dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
 */

import java.util.*;
public class StringToDir {

    public static void main(String[] args) {

        StringToDir o = new StringToDir();

        o.parseStringToDirectories( "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");


    }

    final char SLASH = '\\';

    public void parseStringToDirectories(String dirs) {

        int index = 0;

        List<Integer> path = new ArrayList<>();

        StringBuilder fullPath = new StringBuilder();

        while(index < dirs.length()) {

            int end = dirs.indexOf('\n', index);

            if(end == -1) end = dirs.length();

            String curr = dirs.substring(index, end);


            int start = 0, level = 0;

            while(curr.charAt(start) == '\t') {

                level++;
                start++;

            }

            if(level < path.size()) {

                System.out.println(fullPath.toString());

                while(level < path.size()) {

                        fullPath.delete(path.remove(path.size() - 1), fullPath.length());

                }


            }

            path.add(fullPath.length());

            if(level > 0) fullPath.append(SLASH);

            fullPath.append(curr.substring(start));

            index = end + 1;

        }

        System.out.println(fullPath.toString());


    }

}
