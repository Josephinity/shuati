package Twitter;

/**

 */
public class HackingTime {

    public static void main(String args[]) {
        System.out.println(decrypt("Otjfvknou kskgnl, K mbxg iurtsvcnb ksgq hoz atv." +
                    " Vje xcxtyqrl vt ujg smewfv vrmcxvtg rwqr ju vhm ytsf elwepuqyez.", new int[]{8,2,5,1,2,2,0}));
        //System.out.println(decrypt("Li, ailu jw au facntll.", new int[]{4,0,7,1,3,2,1}));
    }

    public static String decrypt(String input, int[] key) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        for(char c: input.toCharArray()) {
            if(i == key.length) i = 0;
            if(Character.isLetter(c)) {
                char firstLetter = c <= 'Z' ? 'A' : 'a';
                c = (char)(c - key[i]);
                if(c < firstLetter) c = (char)(c + 26);
                i++;
            }
            res.append(c);
        }
        return res.toString();
    }
}
