package FB;

/**
 Implement integer divide(int a, int b) without division or mod operation.
 */
public class Division {

    public static int divide(int dividend, int divisor) {
        //if(divisor == 0) throw new Exception();
        int sign = 1;
        //figure out the sign of the result
        if((dividend < 0 && divisor > 0)
                || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }
        if(dividend < 0) dividend = -dividend;
        if(divisor < 0) divisor = -divisor;
        int n = 1; //get the range of result [n, 2n], where n is doubled every round
        while(dividend > (n << 1) * divisor) {
            n <<= 1;
        }
        //now it's known that the result is between [n, 2n]
        //so in the dividend has a sum of more than n and less than 2n divisors in total
        //to figure out exactly how many divisors sum up to the dividend,
        // break down the problem to result = n + divide(dividend - n * divisor, divisor)
        // next round the dividend becomes (dividend - n * divisor) with n added to the result.
        // proceed to figure out the range [x, 2x] of result for the new dividend, where x can be n/2, n/4, n/8...
        // whenever the x is found, add x to the result and deduce x * divisor from the dividend
        // util n is 0 or dividend is 0

        //If written in math, the formula looks like dividend = ([T/F]* n + [T/F]* n/2 + [T/F]* n/ 4 + [T/F]* n/ 8...) * divisor
        //The quotient will be ([T/F]* n + [T/F]* n/2 + [T/F]* n/ 4 + [T/F]* n/ 8...), [T/F] depends on (dividend - n * divisor) >= 0
        int result = 0;
        while(n > 0 && dividend > 0) {
            if(dividend - n * divisor >= 0) {
                dividend -= n * divisor;
                result += n;
            }
            n >>= 1;
        }
        return result * sign;
    }
}
