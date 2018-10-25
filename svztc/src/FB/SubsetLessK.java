package FB;
import java.util.List;
import java.util.ArrayList;
/**
 给一个排序好的数组，数一数有多少个子集（subset）使得子集里面的最大元素加上最小元素小于K。
 A: [2, 3, 5, 7]  K: 8
 => # of subsets S: Max(S) + Min(S) < K
 => [2] , [2, 3], [2, 5], [2, 3, 5], [3] => #: 5

 Given a sorted array A, find how many subsets of A satisfies MIN(subset) + MAX(subset) < K.
 */
public class SubsetLessK {

    //no dupes
    public static int countSubset(int[] arr, int k) {
        int i = arr.length - 1;
        while(i >= 0 && arr[i] << 1 >= k) i--;
        if(i == -1) return 0;
        int count = 0, j = i;
        while(i >= 0) {
            while(j < arr.length - 1 && arr[j + 1] + arr[i] < k) j++;
            count += Math.pow(2, j - i);
            i--;
        }
        return count;
    }


//    //with dupes
//    void dfs(vector<int> nums, int idx, int &res, vector<int> &cur, int target)
//    {
//        if(idx == nums.size())
//        {
//            return;
//        }
//        else
//        {
//            for(int i = idx; i < nums.size(); i++)
//            {
//                cur.push_back(nums);
//                if((cur.size() == 1 && cur[0] < target) || (cur[0] + cur.back() < target))
//                {
//                    for(auto it : cur)
//                    {
//                        cout<<it<<" ";
//                    }
//                    cout<<endl;
//                    res++;
//                    dfs(nums, i + 1, res, cur, target);
//                }
//                cur.pop_back();
//                while(i < nums.size() - 1 && nums == nums[i + 1])i++;
//            }
//        }
//    }
//    int countSubset2(vector<int> nums, int target)
//    {
//        int res = 0;
//        vector<int> cur;
//        dfs(nums, 0, res, cur, target);
//        return res;
//    }
//    int main()
//    {
//        vector<int> nums = {2, 2, 3, 5, 7};
//        cout<<countSubset2(nums, 8)<<endl;
//
//    }
}
