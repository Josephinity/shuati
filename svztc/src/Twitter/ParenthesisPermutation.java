package Twitter;

/**
 2. Generate valid permutations of expressions based on the count of parenthesis pair

 def bracketsPermutation(n):
    brackets(n, 0, "")
 def brackets(openStock, closeStock, s):
     if openStock is 0 and closeStock is 0:
        print s
     if openStock > 0:
        brackets(openStock-1, closeStock + 1, s + "[")
     if closeStock > 0:
        brackets(openStock, closeStock - 1, s + "]")
 */
public class ParenthesisPermutation {
    static void brackets(int openStock, int closeStock, String s) {
        if (openStock == 0 && closeStock == 0) {
            System.out.println(s);
        }
        if (openStock > 0) {
            brackets(openStock-1, closeStock+1, s + "<");
        }
        if (closeStock > 0) {
            brackets(openStock, closeStock-1, s + ">");
        }
    }
    public static void main(String[] args) {
        brackets(3, 0, "");
    }
}
