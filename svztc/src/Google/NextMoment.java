package Google;

/**
 GG

 Given a string that represents time like "15:31", find the next time that is formed by the numbers in the string(a number can be used more than once).
 For "15:31", the answer should be "15:33".

 */
public class NextMoment {
/**
    def nextMoment(time):
    result = list(time)
    numbers = list(time)
    numbers.pop(2)
    numbers = sorted(set(numbers))

    result[-1] = findNextNumber(numbers, time[-1], '9')
    if result[-1] >= time[-1]:
            return "".join(result)

    result[-2] = findNextNumber(numbers, time[-2], '5')
    if result[-2] > time[-2]:
            return "".join(result)

    result[1] = findNextNumber(numbers, time[1], '9')
    if result[1] > time[1]:
            return "".join(result)

    result[0] = findNextNumber(numbers, time[0], '5')
    if result[0] >= time[0]:
            return "".join(result)
    return "".join(result)
 */


/*

    Give two strings s1 and s2. We can repeat s1 as many times to get s3.  Return true if s3 include s2, return true. Otherwise return false. For example:
    1124, 411 => true
    1124, 412 => false
    1124, 4112411 => true

 */

    boolean find(String s1, String s2) {
        if(s1.isEmpty()) return false;
        int l1 = s1.length(), l2 = s2.length();
        if(l1 >= l2) {
            //return kmp(s1 + s1, s2); //find if s2 is a substring of s1 + s1
        } else {
            for(int i = 0; i < l1; i++) {
                if(match(s1, i, s2)) return true; //find if s2 is made of multiple rotated s1
            }
        }
        return false;
    }

    boolean match(String s1, int j, String s2) {
        int idx = 0;
        while(idx < s2.length()) {
            if(s2.charAt(idx) != s1.charAt(j)) {
                return false;
            }
            idx++;
            j = j % s1.length();
        }
        return true;
    }
}
