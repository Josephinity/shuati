import java.util.LinkedList;
import java.util.List;
/**
 * tim
 */
public class MajorityElement {
    public List<Integer> majorityElement(int[] nums) {
        int counter1 = 0, counter2 = 0, candidate1 = 0, candidate2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                counter1++;
            } else if (num == candidate2) {
                counter2++;
            } else if (counter1 == 0) {
                candidate1 = num;
                counter1 = 1;
            } else if (counter2 == 0) {
                candidate2 = num;
                counter2 = 1;
            } else {
                counter1--;
                counter2--;
            }
        }
        counter1 = 0;
        counter2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                counter1++;
            } else if (num == candidate2) {
                counter2++;
            }
        }
        List<Integer> list = new LinkedList<>();
        int n = nums.length;
        if (counter1 > n / 3) {
            list.add(candidate1);
        }
        if (counter2 > n / 3) {
            list.add(candidate2);
        }
        return list;
    }
}
