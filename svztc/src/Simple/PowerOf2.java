package Simple;

/**
 check if a number is a power of 2
 */
public class PowerOf2 {
    public boolean isPowerOf2(int num) {
        if(num < 0) return false;
        boolean hasOne = false;
        for(int i = 0; i < 31; i++) {
            if(((num >> i) & 1) == 1) {
                if(hasOne) return false;
                else hasOne = true;
            }
        }
        if(!hasOne) return false;
        return true;
    }

    public int countOnes(int num) {
        System.out.println(Integer.toBinaryString(num));
        int count = 0;
        while(num != 0) {
            count++;
            num &= (num - 1);
            System.out.println(Integer.toBinaryString(num));
        }
        return count;
    }
}
