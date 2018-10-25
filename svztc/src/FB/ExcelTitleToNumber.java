package FB;

/**
 *
 * 2017(4-6月) onsite
 Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 */
public class ExcelTitleToNumber {

    public int titleToNumber(String s) {

        int n = 0;

        for(int i = s.length() - 1; i >= 0; i--) {

            n += (s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - 1 - i);

        }

        return n;

    }
}
