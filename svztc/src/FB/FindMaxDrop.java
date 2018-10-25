package FB;

/**
 Given a list of number, there is only one peak or one drop. Find the maximum drop.

 Exps:

 1 -> 2 -> 3 -> 9 -> 3 -> 0 = 9;

 10 -> 4 -> 3 -> 8 = 7 ;
 */
public class FindMaxDrop {

    public static void main(String args[]) {

        System.out.println(maxDrop(new int[]{10,4,3,8}));

    }

    public static int maxDrop(int[] array) {

        int prev = array[0];

        int maxdrop = 0;

        for (int i = 1; i < array.length; i++) {

            if (prev - array[i] > maxdrop) maxdrop = prev - array[i];

            if (prev - array[i] < 0) prev = array[i];


        }

        return maxdrop;

    }

}
