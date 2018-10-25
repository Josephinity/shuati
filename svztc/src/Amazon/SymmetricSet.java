package Amazon;
import java.util.HashSet;
import java.util.Set;
/**

 */
public class SymmetricSet {

    Set<Integer> getSymmetricSet(Set<Integer> a, Set<Integer> b) {
        for(int n: a) {
            if(!b.contains(n)) {
                b.add(n);
            } else {
                b.remove(n);
            }
        }
        return b;
    }
}
