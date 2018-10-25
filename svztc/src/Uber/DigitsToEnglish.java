package Uber;

/**
 Convert a string with digits into a literal representation of the number like: 1001 to one thousand one
 */
public class DigitsToEnglish {

    final String[] largeNumbers = new String[] {""," thousand"," million"," billion"," trillion"," quintillion"};//...etc
    final String[] digits = new String[] {"", " one", " two", " three", " four", " five", " six"," seven"," eight", " nine"};
    final String[] tens = new String[] {"", " ten"," twenty"," thirty"," forty"," fifty"," sixty"," seventy"," eighty"," ninety"};
    final String[] teens = new String[] {" ten", " eleven"," twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen"," eighteen"," nineteen"};


    public static void main(String args[]) {
        DigitsToEnglish t = new DigitsToEnglish();
        System.out.println(t.digitsToEnglish("876545320"));
    }
    String digitsToEnglish(String num) {
        if(num.length() == 1) {
            return digits[Integer.parseInt(num)];
        }
        int len = num.length();
        StringBuilder english = new StringBuilder();
        int largeNumIdx = 0, tripletIdx = 0;
        while(tripletIdx < len) {
            StringBuilder triplet = new StringBuilder();
            if(len > tripletIdx) {
                triplet.append(num.charAt(len - 1 - tripletIdx));
            }
            if(len > tripletIdx + 1) {
                triplet.insert(0, num.charAt(len - 1 - tripletIdx - 1));
            }
            if(len > tripletIdx + 2) {
                triplet.insert(0, num.charAt(len - 1 - tripletIdx - 2));
            }
            if(Integer.parseInt(triplet.toString()) != 0) {
                StringBuilder current = new StringBuilder();
                if (triplet.length() == 3 && triplet.charAt(0) != '0') {
                    //current.append(' ');
                    current.append(digits[triplet.charAt(0) - '0']);
                    current.append(' ');
                    current.append("hundred");
                }
                if (triplet.length() >= 2 && triplet.charAt(triplet.length() - 2) == '1') {
                    //current.append(' ');
                    current.append(teens[triplet.charAt(triplet.length() - 1) - '0']);
                } else {
                    if (triplet.length() >= 2) {
                        //current.append(' ');
                        current.append(tens[triplet.charAt(triplet.length() - 2) - '0']);
                    }
                    //current.append(' ');
                    current.append(digits[triplet.charAt(triplet.length() - 1) - '0']);
                }
                //current.append(' ');
                current.append(largeNumbers[largeNumIdx]);
                english.insert(0, current);
            }
            largeNumIdx += 1;
            tripletIdx += 3;
        }
        return english.toString();
    }
}
