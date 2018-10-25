/**
 Given two strings a and b, figure out how many ways there are
 to insert b into a to make the new string a palindrome.

 test cases:
 'aaaaaaaaaaaaaa', 'aaa' = 15
 '', 'aa' = 1
 'aa', '' = 3
 'aaabba', 'aa' = 2
 'aba', 'ba' = 2
 'asdf', 'fdsa' = 2
 'asdf', 'dsa' = 1
 '','' = 1
 */
public class InsertToMakePalindrome {
    public static void main(String[] args) {
        System.out.println(palindromeCount("asdf", "fds"));
    }
    public static int palindromeCount(String a, String b) {
        int count = 0;
        int start = -1, end = a.length();
        while(start < end - 1) {
            if(start < 0 || a.charAt(start) == a.charAt(end)) {
                String sub = a.substring(start + 1, end);
                if(isPalindrome(b + sub)) {System.out.println(b + sub); count++;}
                if(isPalindrome(sub + b)) {System.out.println(sub + b); count++;}
            } else break;
            start++; end--;
        }
        if(start == end - 1 && (a.isEmpty() || (a.charAt(start) == a.charAt(end)) && isPalindrome(b))) count++;
        return count;
    }

    public static boolean isPalindrome(String str) {
        if(str.isEmpty()) return true;
        int start = 0, end = str.length() - 1;
        while(start < end) {
            if(str.charAt(start) != str.charAt(end)) return false;
            start++; end--;
        }
        return true;
    }

}
