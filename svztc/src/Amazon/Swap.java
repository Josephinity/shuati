package Amazon;

/**
Seems like there are 3 cases:
 1. s1 and s2 share no mutual characters. Then there is no way to further minimize the distance.
 2. s1 and s2 share a pair of characters on crossed positions, such as 'b' and 'c' in "aabaaac" and "aacaaab". Then swap 'b' and 'c'.
 3. s1 and s2 has no crossed pairs but has the same character on different positions. Then swap to align the character.
 */
public class Swap {
    //@return the pair of indices in s2 to swap with.
    public int[] Swap(String s1, String s2) {
        if(s1.length() > s2.length()) {
            return Swap(s2, s1);
        }
        int swap_idx1 = -1, swap_idx2 = -1;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                for (int j = i + 1; j < s1.length(); j++) {
                    if(s1.charAt(i) == s2.charAt(j) && s1.charAt(j) == s2.charAt(i)) {
                        return new int[]{i, j};  //case 2 found
                    } else if(s1.charAt(i) == s2.charAt(j) && s1.charAt(j) != s2.charAt(j)) {
                        swap_idx1 = i;
                        swap_idx2 = j;
                    }
                }
                for(int j = s1.length(); j < s2.length(); j++) {
                    if(s1.charAt(i) == s2.charAt(j)) {
                        swap_idx1 = i;
                        swap_idx2 = j;
                    }
                }
            }
        }
        if(swap_idx1 == -1) return null; //case1
        else return new int[]{swap_idx1, swap_idx2};
    }
}
