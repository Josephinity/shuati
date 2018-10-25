package Google;

/**
 L代表late，A代表absence，O代表正常出席，input是一个string，包含L，A，O，要求不能连续3次late，不能超过1次absence，
 就可以reward这个学生，判断这个学生的出席记录string能不能reward。Follow up：输出长度为n的rewardable的出席string的数量.


 In school a student gets rewarded if he has an attendance record without being absent for more than once or being late for 3 times continuously.
 Given a student's attendance record represented by a string with 3 possible characters 'L'(Late), 'A'(Absent), 'O' (On time),
 check whether the student qualifies for the reward.

 e.g.
 @INPUT (String) "OLLAOOOLLO"
 @RETURN (Boolean) False
 The student does not qualify for reward because "LLA" means he was late for 3 times in a row.

 @INPUT (String) "OLLOAOLLO"
 @RETURN (Boolean) True



 Follow-up:
 If known the length of the attendance string is n, how many possible ways there is to attend school and make sure you get the reward.
 */



/*
Ans:
For a record to be rewardable, 'A' cannot show up more than once.
So we ignore 'A' at the place and consider only the combination and arrangement for 'O' and 'L'.
We create a 2D array arr[i][j] where arr[i][0] is the number of strings with length i and ending letter 'O';
arr[i][1] is the number of strings with length i and ending letter 'L'

*/


public class LAO {

    int count(int n) {

        int[][] arr = new int[n+1][2];
        arr[0] = new int[]{0, 1};
        arr[1] = new int[]{1, 1};

        for (int i = 2; i <= n; i++) {
            arr[i % 2][0] = arr[(i-1) % 2][0] + arr[(i-1) % 2][1];  // the ith letter is O
            arr[i % 2][1] = arr[(i-1) % 2][0] + arr[(i-2) % 2][0];  // the ith letter is L
        }

        int opt = arr[n][0] + arr[n][1];   // case when there is no "A"


        // there are i letters to the left of A and n-i-1 letter to the right of A
        for (int i = 0; i < n; i++) {      // consider all the cases with one A.
            opt += ( arr[i][0] + arr[i][1] ) * ( arr[n-i-1][0] + arr[n-i-1][1] );
        }

        return opt;
    }
}
