package String;

import java.util.List;
import java.util.ArrayList;
/**
 Google
 Given some java code that parse in as a string, find all the comments in the code


 Senarios -
 /**\
 //

 when quoted
 ""
 not quoted
 '"'
 \"


*/


public class FindComments {

    public static List<String> comments(String codes) {
        List<String> comments = new ArrayList<>();
        if(codes == null) return comments;

        int i = 0, n = codes.length();

        while(i < n) {
            char c = codes.charAt(i);

            //找到引号并忽略引号内的内容
            if(c == '\'' || c == '"') {
                i++;
                while(codes.charAt(i) != c) {
                    //遇到escape标志"\\"就忽略下一个字符
                    if(codes.charAt(i) == '\\') i++;
                    i++;
                }

            } else if(c=='/'){
                StringBuilder comment = new StringBuilder();
                char type = codes.charAt(++i);
                i++;
                if(type == '*') {
                    //  读取/* */这种comment，以遇到"*/"为结束标志
                    while(codes.charAt(i) != '*' || codes.charAt(i + 1) != '/') {
                        comment.append(codes.charAt(i));
                        i++;
                    }
                } else {
                    //  读取//这种comment,以i == n或遇到"\\n"作为结束条件
                    while (i < n && ( codes.charAt(i) != '\\' || i + 1 == n || codes.charAt(i + 1) != 'n')) {
                        char a = codes.charAt(i);
                        if(a == '\\') {
                            //corner case: 处理在这种comment里遇到"\\\\n"的情况，escape掉这个换行符
                            //遇到escape标志"\\"且下一个字符不是‘n’就忽略下一个字符
                            comment.append(codes.charAt(i + 1));
                            i++;
                        }
                        comment.append(a);
                        i++;
                    }
                }
                comments.add(comment.toString());
                i++;
            }
            i++;
        }
        return comments;
    }
}
