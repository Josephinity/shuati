package Google;
import com.sun.java.browser.plugin2.DOM;

import java.util.ArrayList;
import java.util.List;
/**
 Transform the html string to DOM tree
 */

class DOMNode {
    String element; //such as "h1", "body", "title"
    boolean leaf;
    //List<String> attributes; //such as "class", "href", "style"
    List<DOMNode> subElements;

    public DOMNode(String ele, boolean isLeaf) {
        element = ele;
        if(isLeaf) {
            leaf = true;
        } else {
            //attributes = new ArrayList<>();
            subElements = new ArrayList<>();
        }
    }

}
public class DOMTree {

    DOMNode document;


}
