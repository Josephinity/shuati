package Stack;
import java.util.Stack;
/**
 Leetcode 84
 */
public class MaxHistogram {

//    public static void main(String[] args) {
//
//        MaxHistogram m = new MaxHistogram();
//
//        m.largestRectangleArea(new int[]{4, 2,0,3, 2, 5});
//
//    }

        public int largestRectangleArea(int[] heights) {

            int max = 0, i = 0, minHeight = Integer.MAX_VALUE, minIndex = -1;

            Stack<Integer> stack = new Stack<>();

            while(i < heights.length) {

                if(stack.isEmpty() || heights[stack.peek()] <= heights[i]) {

                    stack.push(i);

                    i++;

                } else {

                    int endIndex = stack.peek();

                    while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {

                        int startIndex = stack.pop();

                        max = Math.max((stack.isEmpty() ? endIndex + 1 :(endIndex - stack.peek())) * heights[startIndex], max);
                        //max = Math.max((i - startIndex) * heights[i], max);
                    }

                }

            }
            //System.out.println(stack + "  " + max);
            if(!stack.isEmpty()) {

                int endIndex = stack.peek();

                while(!stack.isEmpty()) {

                    int startIndex = stack.pop();

                    max = Math.max((stack.isEmpty() ? endIndex + 1 : (endIndex - stack.peek())) * heights[startIndex], max);

                }
            }

            return max;
        }

}
