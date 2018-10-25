/**
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete at most two transactions


 testcases:
 [] = 0
 [1, 1] = 0
 [4, 2, 1] = 0
 [1,4,5,2,5,6,7,2,5,1,1,5,7,13,5,6,9,3] = 18
 [1,7,4,6,22] = 21

 */
public class BuyingStockTwice {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,4,5,2,5,6,7,2,5,1,1,5,7,13,5,6,9,3}));
    }

    public static int maxProfit(int[] prices) {
        if(prices.length <= 1) {
            return 0;
        }
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE,
                release1 = 0, release2 = 0;
        for(int price: prices) {
            if(release1 < price + buy1) release1 = price + buy1;
            if(buy1 < -price) buy1 = -price;
            if(release2 < price + buy2) release2 = price + buy2;
            if(buy2 < release1 - price) buy2 = release1 - price;
        }
        return release2;
    }
}
