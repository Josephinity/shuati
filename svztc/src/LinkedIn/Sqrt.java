package LinkedIn;

/**
 */
public class Sqrt {
    int sqrt(int x) {
        //check for x >= 0 if necessary
        int r = x;
        while (r * r > x)
            r = (r + x / r) / 2;
        return r ;
    }
}
