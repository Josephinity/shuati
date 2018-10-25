/**
 */
public class MultiplyBigNumbers {
    public static void main(String[] args) {
        System.out.println(multiply("738304839084", "" + "999999999999"));
        System.out.println((long)(738304839084L * 999999999999L));
    }

    public static String multiply(String a, String b) {

        int m = a.length(), n = b.length();
        int[] num1 = new int[m], num2 = new int[n];
        for(int i = m - 1; i >= 0; i--) {
            num1[m - 1 - i] = a.charAt(i) - '0';
        }
        for(int i = n - 1; i >= 0; i--) {
            num2[n - 1 - i] = b.charAt(i) - '0';
        }
        int[] res = new int[m + n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                res[i + j] += num1[i] * num2[j];
            }
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < m + n - 1; i++) {
            res[i + 1] += res[i] / 10;
            str.insert(0, res[i] % 10);
        }
        if(res[m + n - 1] > 0) str.insert(0, res[m + n - 1]);
        return str.toString();
    }
}
