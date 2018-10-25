package Next;

/**
 Given a sorted array, with some empty slots and a target value, ret the index for the target value.
 If not existed, return -1;

 Tim
 */
public class FindNumInArrayWithHoles {

    public static void main(String[] args) {
        RandomGenerator rg = new RandomGenerator();
        int[] nums = new int[rg.randomNumber(0, 50)];
        int prev = 0;
        for(int i = 0; i < nums.length; i++) {
            if(rg.randomBoolean(.7)) {
                nums[i] = rg.randomNumber(prev, prev + 5);
                prev = nums[i];
            }
        }
        for(int n: nums) System.out.print(n + " ");
        int t = nums[rg.rand.nextInt(nums.length)];
        while(t == 0) {
            t = nums[rg.rand.nextInt(nums.length)];
        }
        t++;

        System.out.println("target is " + t);

        int index = find(nums, t);
        System.out.println("index " + index);

        if(index != -1) System.out.println(nums[index]);
        verify(nums, t);

    }

    private static int find(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while(l <= r && nums[l] == 0) l++;
        if(l > r) return -1;
        while(l <= r && nums[r] == 0) r--;

        while(l <= r) {

            if(t < nums[l] || t > nums[r]) return -1;
            if(t == nums[l]) return l;
            if(t == nums[r]) return r;
            int mid = (l + r) / 2;
            int left = mid, right = mid;
            if(nums[mid] == 0) {
                while(left > l && nums[left] == 0) {
                    left--;
                }
                while(right < r && nums[right] == 0) {
                    right++;
                }
                if(left == l && right == r) return -1;
                else if (left == l) left = right;
                else if (right == r) right = left;
            }
            if(t >= nums[right]) {
                l = right;
                while(--r > l && nums[r] == 0) r--;
            }
            else if(t <= nums[left]) {
                r = left;
                while(++l < r && nums[l] == 0) l++;
            }
            else return -1;
        }
        return -1;
    }

    private static void verify(int[] nums, int t) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == t) {
                System.out.println(i);
                return;
            }
            else if(nums[i] > t) {
                System.out.println(-1);
                return;
            }
        }
    }
}
