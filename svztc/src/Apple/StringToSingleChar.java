package Apple;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 Given three letters ABC, where AB->C, AC->B, BC->A (sequence doesn’t matter). Get the length of the path to convert from a given string to a single character.

 For example, “ABACB” goes to “ACCB” (based on AB ->C, convert s[1] and s[2] to C)
 “ACCB” goes to “BCB” (based on AC->B)
 “BCB” goes to “AB”
 “AB” goes to “C”
 So it takes 4 steps to change the given string into a single character.
 If a given string cannot be resized to 1 character, such as “AAA” or "ABACABB", return -1.

 */
public class StringToSingleChar {


    public static void main(String[] args) {

        StringToSingleChar t = new StringToSingleChar("ABACABB");
        System.out.println(t.pathLen());

    }

    List<Character> str;

    public StringToSingleChar(String s) {

        str = new ArrayList<>();

        for(char c: s.toCharArray()) {

            str.add(c);
        }

    }

    public int pathLen() {

        if(str.size() == 0) return -1;

        if(str.size() == 1) return 0;

        if(invalidToConvert()) return -1;

        for(int i = 0; i < str.size() - 1; i++) {

            char curr = str.get(i), next = str.get(i + 1);

            if(curr != next) {

                str.set(i, convert(curr, next));        //backtracking, try to convert any two adjacent chars in str
                str.remove(i + 1);

                int steps = pathLen();

                if(steps >= 0) return steps + 1;

                str.set(i, curr);

                str.add(i + 1, next);

            }

        }

        return -1;

    }

    private boolean invalidToConvert() {    //check if given string is invalid like "AAAAA..." or "BBBBB..." or "CCCCC..."

        for(int i = 0; i < str.size() - 1; i++) {

            if(str.get(i) != str.get(i + 1)) return false;

        }

        return true;

    }

    private char convert(char a, char b) {

        if(a != 'C' && b != 'C') return 'C';

        if(a != 'B' && b != 'B') return 'B';

        return 'A';

    }

}
