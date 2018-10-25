package Google;

/**

 */
public class MaximumVacationDays {

    public static void main(String[] args) {
        System.out.println(
            maxVacation(
                    new int[][]{
                            {0,0,0},
                            {0,0,0},
                            {0,0,0}
                    },
                    new int[][] {
                            {1,1,1},
                            {6,0,3},
                            {3,3,3}
                    }
            )
        );
    }

    static void print(int[][] m) {
        for(int[] x: m) {
            for(int a: x) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static int maxVacation(int[][] flights, int[][] days) {
        int weeks = days[0].length;
        int number_of_cities = days.length;
        int[][] vacation = new int[number_of_cities][weeks];
        for(int w = weeks - 1; w >= 0; w--) {
            for(int c = 0; c < number_of_cities; c++) {
                if(w == weeks - 1) {
                    vacation[c][w] = days[c][w];
                } else {
                    for(int destination = 0; destination < number_of_cities; destination++) {
                        if (flights[c][destination] == 1 || c == destination) {
                            vacation[c][w] = Math.max(vacation[c][w], days[c][w] + vacation[destination][w + 1]);
                        }
                        print(vacation);
                    }
                }
            }

        }
        int max = vacation[0][0];
        for(int c = 1; c < number_of_cities; c++) {
            if(flights[0][c] == 1) {
                max = Math.max(vacation[c][0], max);
            }
        }
        return max;
    }
}
