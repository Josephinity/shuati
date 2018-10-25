package Google;

import java.util.Stack;

/**
 Similar with valid parentheses, but here we check validity of tag matching of a html string.
 Jan 2018
 */
public class ValidateHTML {
    boolean validateHTML(String html) {
        Stack<String> tags = new Stack<>();
        int i = 0, strlen = html.length();
        String tag;
        while(i < strlen) {
            if(html.charAt(i) == '<') {
                int closingAt = html.indexOf('>', i);
                if(closingAt == -1) return false;
                if(html.charAt(i + 1) == '/') {
                    tag = html.substring(i + 2, closingAt);
                    if(tags.isEmpty() || !tags.pop().equals(tag)) return false;
                } else {
                    //opening tag might be <tagname id="tag1" class="col">, Therefore tagname is between'<' and ' '.
                    int whitespaceAt = html.indexOf(' ', i);
                    if(whitespaceAt == -1 || whitespaceAt > closingAt) {
                        tag = html.substring(i + 1, closingAt);
                    } else {
                        tag = html.substring(i + 1, whitespaceAt);
                    }
                    tags.push(tag);
                }
                i = closingAt + 1;
            } else i++;
        }
        if(!tags.isEmpty()) return false;
        return true;
    }

}
