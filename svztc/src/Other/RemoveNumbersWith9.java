package Other;

/**

 * Houzz:
 *
 1. Remove any number containing 9 like 9, 19, 29, ... 91, 92, ... 99, 109...
 Write a function that returns the nth number. E.g.  newNumber(1) = 1  newNumber(8) = 8, newNumber(9) = 10, 最后给了hint把数变成9-based
 2. kSum.... expect better runtime than backtracking... Consider 3sum's backtracking complexity is worse than n^2, which use's two sum's two pointer approach.
 3. Design Excel. Implement
 int get(string cell)
 void put(string cell, string expr). expr can be "A1= B1+1"
 这题的关键在于，要解决各个cell的dependence问题. 比如说call put(B1, "3")之后，同时也要update A1的值。会牵扯到topo sort的问题。总之这题是design题，就看你有没有意识到这种dependence.
 4. Design Intagram
 5. Android lock pattern from leetcode



 1.
 Remove any number containing 9 like 9, 19, 29, ... 91, 92, ... 99, 109...
 Write a function that returns the nth number. E.g.  newNumber(1) = 1  newNumber(8) = 8, newNumber(9) = 10, 最后给了hint把数变成9-based
 */
public class RemoveNumbersWith9 {

    //convert number to base 9
    public static int x9(int n) {
        int num = 0, c = 0;
        while(n > 0) {
            int mod = n % 9;
            num += mod * Math.pow(10, c);
            n /= 9;
            c++;
        }
        return num;
    }

}
