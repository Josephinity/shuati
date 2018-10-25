package Google;

/**
 Process a token stream, each token holding a token value and a timestamp.
 Only output the values that didn't up in the past 10 seconds.
 Follow-up: An additional rule for the filter: output a token only if a duplicated token value does not show up within 10 seconds in the future as well as from the past of
 the token's timestamp.
 */
import java.util.*;

class Token {
    Date ts;//timestamp
    int value;
}
public class DedupTokenStream {

    Deque<Token> queue = new ArrayDeque<>();
    Map<Integer, Date> map = new HashMap<>();

}
