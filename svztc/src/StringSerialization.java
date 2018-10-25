/**
 Given a list of strings, serialize them into a single string.
 And the string should be able to transform back into the original list.

 test cases:
 ['df', '93\t3', 'te  ', '}', '\$']

 [] (empty)

 [''] (one empty string)

 None


 */



import java.util.*;
public class StringSerialization {
    public static void main(String args[]) {
        List<String> strs = deserialize("2-df3-9334-te\tt1-}0-0-10-----------");
        System.out.println(strs);
        System.out.println(serialize(strs));
    }

    public static String serialize(List<String> strs) {
        StringBuilder ret = new StringBuilder();
        for(String str: strs) {
            int size = str.length();
            ret.append(size);
            ret.append('-');
            ret.append(str);
        }
        return ret.toString();
    }

    public static List<String> deserialize(String str) {
        List<String> strs = new ArrayList<>();

        int len = str.length(), i = 0;
        while(i < len){
            int start = i;
            while(str.charAt(i) != '-') i++;
            int size = Integer.parseInt(str.substring(start, i));
            i += 1 + size;
            if(size > 0) strs.add(str.substring(i - size, i));
            else strs.add("");
        }

        return strs;
    }
}

