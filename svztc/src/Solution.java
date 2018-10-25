/**
 */

import Uber.DigitsToEnglish;

import java.util.*;

public class Solution {

    public static void main(String[] args) {

        System.out.println(BinarySearchRows(new int[]{6, 7, 9}, 0, 2, 9));

    }

    private static int BinarySearchRows(int[] matrixRow, int low, int high, int target){
        while(low <= high){
            int mid = low + (high - low)/2;
            if(matrixRow[mid] == target){
                return mid;
            }else if(matrixRow[mid] > target){
                high = mid - 1;
            }else if(matrixRow[mid] < target){
                low = mid + 1;
            }
        }
        return -1;
    }
//
//    int minNumberOfSwaps(String s1,String s2) {
//        //if(!isAnagram(s1, s2)) return Integer.MAX_VALUE; //assume s1 and s2 are anagrams
//        int step = 0;
//        Set<String> s1Derives = new HashSet<>();
//        Set<String> s2Derives = new HashSet<>();
//        s1Derives.add(s1);
//        s2Derives.add(s2);
//        Set<String> visited = new HashSet<>();
//        int len = s1.length();
//        while(!containsSameString(s1Derives, s2Derives)) {
//            Set<String> set = step % 2 == 0 ? s1Derives: s2Derives;
//            Set<String> nextLevel = new HashSet<>();
//            for(String s: set) {
//                for (int i = 0; i < len; i++) {
//                    for (int j = i; j < len; j++) {
//                        if(s.charAt(i) != s.charAt(j)) {
//                            char[] charArray = swap(s.toCharArray(), i, j);
//                            String derived = new String(charArray);
//                            if(!visited.contains(new String(derived))) {
//                                nextLevel.add(derived);
//                                visited.add(derived);
//                            }
//                        }
//                    }
//                }
//            }
//            if(step % 2 == 0) s1Derives = nextLevel;
//            else s2Derives = nextLevel;
//            step++;
//        }
//        return step;
//    }



    int maxRecSell(int k, int[] prices, int i) {
        if(i == 0) return 0;
        if(k == 0) return 0;
        int profit1 = prices[i] + maxRecBuy(k, prices, i - 1);
        int profit2 = maxRecSell(k, prices, i - 1);
        return Math.max(profit1, profit2);
    }

    int maxRecBuy(int k, int[] prices, int i) {
        if(i == 0) return -prices[0];
        int profit1 = maxRecSell(k - 1, prices, i - 1) - prices[i];
        int profit2 = maxRecBuy(k, prices, i - 1);
        return Math.max(profit1, profit2);
    }

    public int maxProfit(int k, int[] prices) {
        if (k>prices.length/2) {
            int sum = 0;
            for(int i = 1;i < prices.length;i++){
                if(prices[i] > prices[i-1]){
                    sum += prices[i] - prices[i-1];
                }
            }
            return sum;
        }
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];        //1,2,4,2,5,7,2,4,9,0   i = 9
        Arrays.fill(buy, Integer.MIN_VALUE);//
        //buy: Min,  0,  6,  13,   15
        //sell:  0,  6,  13,  15,  15
        int profit = 0;
        for(int i = 0; i < prices.length; i++) {
            for(int j = k; j >= 1; j--) {
                sell[j] = Math.max(buy[j] + prices[i], sell[j]);
                //profit = Math.max(sell[j], profit);
                buy[j] = Math.max(sell[j - 1] - prices[i], buy[j]);
            }
        }
        return sell[k];
    }

    int balanced(TreeNode root) { //-1 denotes unbalanced tree
        if(root == null) return 0;
        int depthLeft = depth(root.left);
        int depthRight = depth(root.right);
        if(depthLeft == -1 || depthRight == -1 || Math.abs(depthLeft - depthLeft) > 1) return -1;
        return Math.max(depthLeft, depthRight) + 1;
    }

    public boolean symmetric(TreeNode root) {
        return symmetric(root, root);
    }
    private boolean symmetric(TreeNode r1, TreeNode r2) {
        if(r1 == null && r2 == null) return true;
        if(r1 == null || r2 == null || r1.val != r2.val) return false;
        return symmetric(r1.left, r2.right) && symmetric(r1.right, r2.left);
    }

    public int mps(TreeNode root) {
        if(root == null) return 0;
        int leftPathSum = mps(root.left);
        int rightPathSum = mps(root.right);
        return Math.max(leftPathSum, rightPathSum) + root.val;
    }

    public int depth(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }


    public TreeNode cloneTreeDFS(TreeNode root) {
        if(root == null) return null;
        TreeNode clone = new TreeNode(root.val);
        clone.left = cloneTreeDFS(root.left);
        clone.right = cloneTreeDFS(root.right);
        return clone;
    }

    public TreeNode cloneTreeBFS(TreeNode root) {
        if(root == null) return root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        Map<TreeNode, TreeNode> clone = new HashMap<>();
        TreeNode cloneRoot = new TreeNode(root.val);
        clone.put(root, cloneRoot);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.left != null) {
                queue.add(cur.left);
                TreeNode leftClone = new TreeNode(cur.left.val);
                clone.get(cur).left = leftClone;
                clone.put(cur.left, leftClone);
            }
            if(cur.right != null) {
                queue.add(cur.right);
                TreeNode rightClone = new TreeNode(cur.right.val);
                clone.get(cur).right = rightClone;
                clone.put(cur.right, rightClone);
            }
        }
        return cloneRoot;
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int prev = 1, num = 0, op = '*', i = 0;
        while(i < s.length()) {
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            } else if(s.charAt(i) == '*' || s.charAt(i) == '/') {
                prev = op == '*' ? prev * num: prev / num;
                num = 0;
                op = s.charAt(i);
            } else if(s.charAt(i) == '+' || s.charAt(i) == '-'){
                prev = op == '*' ? prev * num: prev / num;
                stack.push(prev);
                num = 0;
                prev = s.charAt(i) == '+' ? 1: -1;
                op = '*';
            }
            i++;
        }
        prev = op == '*' ? prev * num: prev / num;
        stack.push(prev);
        num = 0;
        while(!stack.isEmpty()) {
            num += stack.pop();
        }
        System.out.println(num);
        return num;
    }



    public List<Point> kNearest(List<Point> points, Point origin, int k) {
        PriorityQueue<Point> queue = new PriorityQueue<>((o1,o2)->(distance(o1, origin) - distance(o2,origin)));
        for(Point p: points) {
            queue.add(p);
        }
        List<Point> res = new ArrayList<>();
        while(k > 0) {
            res.add(queue.poll());
            k--;
        }
        return res;
    }

    private int distance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }


    int[] twoSum(List<Integer> array, int target) {
        Collections.sort(array);
        int j=array.size()-1;
        int i=0;
        while(j>i){
            if (array.get(i)+array.get(j)>target){
                j=j-1;
            }
            else if(array.get(i)+array.get(j)<target){
                i=i+1;
            }
            else{
                int[] tuple=new int[] {i+1,j+1};
                return tuple;
            }
        }
        return new int[] {-1,-1};
    }

    void finbonacci(int n) {//print series up to the nth number
        int curr = 1;
        int prev = 0;
        while(n > 0) {
            System.out.print(curr + "  ");
            int next = curr + prev;
            prev = curr;
            curr = next;
            n--;
        }
    }

    public List<int[]> getPairs(int[] array, int[] sumArray) {
        Arrays.sort(array);
        List<int[]> result = new ArrayList<>();
        for(int sum: sumArray) {
            twoSum(result, array, sum);
        }
        return result;
    }
    private void twoSum(List<int[]> result, int[] array, int sum) {
        int l = 0, r = array.length - 1;
        while(l < r) {
            int s = array[l] + array[r];
            if (s == sum) {
                result.add(new int[]{array[l], array[r]});
                l++; r--;
            } else if(s < sum){
                l++;
            } else {
                r--;
            }
        }
    }

    public int binarySearch(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid] < target){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return  -1;
    }


    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public List<Point> findPath(int[][] grid) {
        List<Point> path = new ArrayList<>();
        if(dfs(path, 0, 0, grid)) return path;
        return null;
    }
    private boolean dfs(List<Point> path, int x, int y, int[][] grid) {
        if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0) return false;
        if(x == grid.length - 1 && y == grid[0].length - 1) return true;
        path.add(new Point(x, y));
        grid[x][y] = 0;
        if(dfs(path, x + 1, y, grid)) return true;
        if(dfs(path, x - 1, y, grid)) return true;
        if(dfs(path, x, y + 1, grid)) return true;
        if(dfs(path, x, y - 1, grid)) return true;
        path.remove(path.size() - 1);
        return false;
    }

    public void bfs(TreeNode root) {
        if(root == null) return;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int n = 1;
        while(!q.isEmpty()) {
            int children = 0;
            int sum = 0;
            while(n > 0) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.add(node.left);
                    children++;
                }
                if (node.right != null) {
                    q.add(node.right);
                    children++;
                }
                n--;
            }
            n = children;
            sum = 0;
            System.out.print(sum);
        }
    }


    public TreeNode preorderAndInorderToTree(int[] preorder, int[] inorder, int s1, int e1, int s2, int e2) {
        if(preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[s1]);
//        int rootIndex = find(inorder, preorder[s1], s1); //find index of preorder[s1] starting from s1
//        root.left = preorderAndInorderToTree(preorder, inorder, s1 + 1, s1 + rootIndex - s2, s2, rootIndex - 1);
//        root.right = preorderAndInorderToTree(preorder, inorder, s1 + rootIndex - s2 + 1, e1, rootIndex + 1, e2);
        return root;
    }


    public void getInorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while(i < preorder.length) {
            if(stack.isEmpty() || preorder[i] < stack.peek()) {
                stack.push(preorder[i]);
                i++;
            } else {
                System.out.println(stack.pop());
            }
        }
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public int quickselect(int[] a, int k) {
        int lo = 0, hi = a.length - 1;
        while(lo <= hi) {
            int pivotIndex = partition(a, lo, hi);
            if(pivotIndex == k - 1) {
                return a[pivotIndex];
            } else if(pivotIndex < k - 1) {
                lo = pivotIndex + 1;
            } else {
                hi = pivotIndex - 1;
            }
        }
        //k is out of range
        return 0;
    }

    int partition(int[] a, int lo, int hi) {
        int pivot = a[lo];
        int pivotIndex = lo;
        lo++;
        while(lo <= hi) {
            if(a[lo] <= pivot) lo++;
            if(a[hi] > pivot) hi--;
            if(a[lo] > pivot && a[hi] <= pivot) {
                //swap(a, lo, hi);
                lo++;
                hi--;
            }
        }
        //swap(a, pivotIndex, hi);
        return hi;
    }



    String shortestPangram(String s, Map<Character, Integer> dict) {
        if(dict.isEmpty()) return "";
        Map<Character, Integer> letters = new HashMap<>();
        int size = dict.size();
        String minPangram = null;
        int l = 0, r = 0;
        while(r < s.length()) {
            char c = s.charAt(r);
            if(dict.containsKey(c)) {
                letters.put(c, letters.getOrDefault(c, 0) + 1);
                if(letters.get(c) == dict.get(c)) {
                    size--;
                    if(size == 0) {
                        if (minPangram == null || r - l + 1 < minPangram.length()) {
                            minPangram = s.substring(l, r + 1);
                        }
                        while(size == 0 || !dict.containsKey(s.charAt(l))) {
                            char discard = s.charAt(l);
                            if(dict.containsKey(discard)) {
                                letters.put(discard, letters.get(discard) - 1);
                                if(letters.get(discard) < letters.get(discard)) {
                                    size++;
                                }
                            }
                            l++;
                        }
                    }
                }
            }
            r++;
        }
        return minPangram;
    }

    public double solve(String equation) {
        int indexEqualSign = equation.indexOf('=');
        int[] left = simplify(equation.substring(0, indexEqualSign));
        int[] right = simplify(equation.substring(indexEqualSign + 1));
        return 1.0 * (left[0] - right[0]) / (right[1] - left[1]);
    }

    private int[] simplify(String exp) {
        int i = 0;
        int num = 0, sign = 1;
        int sum = 0, factor = 0;
        while(i < exp.length()) {
            char c = exp.charAt(i);
            if(c == ' ') {
            } else if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else {
                if (c == 'x') {
                    factor += sign * (num == 0 ? 1: num);
                } else {
                    sum += num * sign;
                    sign = c == '+' ? 1 : -1;
                }
                num = 0;
            }
            i++;
        }
        sum += sign * num;
        return new int[]{sum, factor};
    }

    /*
    Facebook
    You have a tree with nodes of different values.
    You want to  find the longest path within this tree between nodes that have the same value.*/

    class Wrapper {
        int maxPath;
        Map<Integer, Integer> map; //a value of node:level
        public Wrapper(int maxPath) {
            map = new HashMap<>();
            this.maxPath = maxPath;
        }
    }
    public int findPathSameValue(TreeNode root) {
        return findPathSameValue(root, 0).maxPath;
    }
    Wrapper findPathSameValue(TreeNode root, int level) {
        if(root == null) {
            return new Wrapper(-1);
        }
        Wrapper leftWrapper = findPathSameValue(root.left, level + 1);
        Wrapper rightWrapper = findPathSameValue(root.right, level + 1);
        int maxPath = Math.max(leftWrapper.maxPath, rightWrapper.maxPath);
        for(int value: leftWrapper.map.keySet()) {
            if(rightWrapper.map.containsKey(value)) {
                int pathLen = leftWrapper.map.get(value) + rightWrapper.map.get(value) - 2 * level;
                maxPath = Math.max(maxPath, pathLen);
            }
        }
        if(leftWrapper.map.containsKey(root.val)) {
            maxPath = Math.max(maxPath, leftWrapper.map.get(root.val) - level);
        }
        if(rightWrapper.map.containsKey(root.val)) {
            maxPath = Math.max(maxPath, rightWrapper.map.get(root.val) - level);
        }
        Wrapper ret = new Wrapper(maxPath);
        ret.map.putAll(leftWrapper.map);
        for(int key: rightWrapper.map.keySet()) {
            if(!ret.map.containsKey(key) || ret.map.get(key) < rightWrapper.map.get(key)) {
                ret.map.put(key, rightWrapper.map.get(key));
            }
        }
        ret.map.put(root.val, level);
        return ret;
    }

    /**
     *
     * Given two binary trees, write a method to compare if one is  a subset of the other**/
    boolean isSubTree(TreeNode root, TreeNode sub) {
        if(root == null) return false;
        if(root.val == sub.val) return isSameTree(root, sub);
        return isSubTree(root.left, sub) || isSubTree(root.right, sub);
    }
    boolean isSameTree(TreeNode root, TreeNode other) {
        if(root == null && other == null) return true;
        if(root == null || other == null || root.val != other.val) return false;
        return isSameTree(root.left, other.left) && isSameTree(root.right, other.right);
    }


    /**Print all simplified fractions between 0 and 1 that have  denominators less than or equal to n.**/
    void printFractions(int n) {
        for(int denom = n; n >= 2; n--) {
            for(int num = 1; num < denom; num++) {
                if(GCD(denom, num) == 1) {
                    System.out.println(num + "/" + denom);
                }
            }
        }
    }
    int GCD(int a, int b) {
        if (b==0) return a;
        return GCD(b,a%b);
    }

    /**
     * Google
     * The question is to find the number of connected components  for an input of double linked list.
     * Given an input of cycled double linked list, please describe an algorithm to find the number of connected components. **/
//    int numberOfConnectedComponents(List<Node> nodes) {
//        if(nodes.size() == 0) return 0;
//        Set<Node> visited = new HashSet<>();
//        int numberOfComponents = 0;
//        for(Node node: nodes) {
//            if(!visited.contains(node)) {
//                if(visited.contains(node.prev) && visited.contains(node.next)) {
//                    numberOfComponents--;
//                } else if(!visited.contains(node.prev) && !visited.contains(node.next)) {
//                    numberOfComponents++;
//                }
//            }
//        }
//        if(numberOfComponents > 0) return numberOfComponents;
//        return 1; //every node in the cyclic linked list is given
//    }



    /**
     * Google
     * Given a list of non-negative numbers and a target integer k,
     * write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k,
     * that is, sums up to n*k where n is also an integer.**/
    boolean hasFactorKSubArray(int[] arr, int k) {
        for(int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1]; //becomes a prefix sum array of arr (can be recovered if necessary)
        }
        for(int hi = arr.length - 1; hi >= 1; hi-- ) {
            for(int lo = 0; lo <= hi - 1; lo++) {
                if((arr[hi] - lo == 0 ? 0: arr[lo - 1]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

//    class Segment {
//        int start;
//        int end;
//        int driverID;
//        ...
//    }
//
//    int findDriver(List<Segment> travelTime) {
//        int maxTime = 0, driver = -1;
//        Collections.sort(travelTime, (o1,o2)->(o1.start - o2.start));
//        Map<Integer, int[]> tripChain = new HashMap<&gt();//map of <driverID, [endTime, chainLength]&gt
//        for(Segment s : travelTime) {
//            if(!tripChain.containsKey(s.driverID) {
//                tripChain.put(s.driverID, new int[]{s.end, s.end - s.start};
//            } else {
//                int[] time = tripChain.get(s.driverID);
//                time[1] += s.start >= time[0] ? s.end - s.start: (s.end > time[0] ? s.end - time[0]: 0);
//                time[0] = Math.max(time[0], s.end);
//            }
//            if(tripChain.get(s.driverID)[1] > maxTime) {
//                driver = s.driverID;
//                maxTime = tripChain.get(driver)[1];
//            }
//        }
//        return driver;
//    }

//    Node reverse(Node head) {
//        Node reversed = null, curr = head;
//        while(curr != null) {
//            Node next = curr.next;
//            curr.next = reversed;
//            reversed = curr;
//            curr = next;
//        }
//        return reversed;
//    }


//    public int read(char[] buf, int n) {
//        boolean eof = false;      // end of file flag
//        int total = 0;            // total bytes have read
//        char[] tmp = new char[4]; // temp buffer
//
//        while (!eof && total < n) {
//            int count = read4(tmp);
//
//            // check if it's the end of the file
//            eof = count < 4;
//
//            // get the actual count
//            count = Math.min(count, n - total);
//
//            // copy from temp buffer to buf
//            for (int i = 0; i < count; i++)
//                buf[total++] = tmp[i];
//        }
//
//        return total;
//    }
//
//    public int read(char[] buf, int n) {
//        boolean eof = false;      // end of file flag
//        int total = 0;            // total bytes have read
//        char[] tmp = new char[4]; // temp buffer
//
//        while (!eof && total < n) {
//            int count = read4(tmp);
//
//            // check if it's the end of the file
//            eof = count < 4;
//
//            // get the actual count
//            count = Math.min(count, n - total);
//
//            // copy from temp buffer to buf
//            for (int i = 0; i < count; i++)
//                buf[total++] = tmp[i];
//        }
//
//        return total;
//    }

//    Node reverseEvery3(Node head) {
//        Node dummy = new Node(0);
//        Node tail = dummy;
//        Node ptr = head;
//        while(ptr != null) {
//            tail.next = ptr;
//            Node newTail = ptr;
//            if(ptr.next != null) { //if current triplet has a second node
//                ptr = ptr.next; //move ptr to second node in triplet
//                Node last = ptr.next; //third node in the triplet
//                ptr.next = tail.next; //insert second node between previous triplet and first node in current triplet
//                tail.next = ptr;
//                if(last != null) { //if current triplet has a third node
//                    ptr = last.next;
//                    last.next = tail.next;//insert third node between previous triplet and second node
//                    tail.next = last;
//                } else break;
//            } else break;
//            tail = newTail;
//        }
//        return dummy.next;
//    }



    int findConsecutive1s(int[] arr) {
        int start = 0, maxWindow = 0;
        int currentWindow = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 1) {//if the current window has the longest consecutive 1s
                currentWindow++;
                if(currentWindow > maxWindow) {
                    start = i - currentWindow + 1; //set max window as current window
                    maxWindow = currentWindow;
                }
            }
        }
        return maxWindow; //returns the size of longest consecutive 1s.
                            // can also return the start of the consecutive 1s - @PARAM start, if necessary
    }

//    Node delete(Node head, int toDelete) {
//        Node dummy = new Node(Integer.MIN_VALUE);
//        dummy.next = head;
//        Node ptr = dummy;
//        while(ptr.next != null) {
//            if(ptr.next.val == toDelete) {
//                ptr.next = ptr.next.next; //delete node with given value
//            } else if(ptr.next.val > toDelete) {
//                break;
//            } else {
//                ptr = ptr.next;
//            }
//        }
//        return dummy.next;
//    }
//
//    boolean isPreorder(Node[] nodes) {
//        if(nodes.length == 0) return true;
//
//        Stack<Integer> st = new Stack<>();
//        st.push(nodes[0].ID);
//
//        int i = 1;
//        while(i < nodes.length) {
//            if(st.isEmpty()) return false;
//            if(st.peek() == nodes[i].parentID) {
//                st.push(nodes[i].ID);
//                i++;
//            } else {
//                st.pop();
//            }
//        }
//        return true;
//    }

    //Extra space O(1) Runtime O(nlogn)
    List<Integer> getFibonacciNumber(int[] nums) {
        List<Integer> fibonacciNumbers = new ArrayList<>();
        Arrays.sort(nums);
        int fib1 = 1, fib2 = 1;
        for(int i = 0; i < nums.length;) {
            if(nums[i] < fib2) {
                i++;
            } else if(nums[i] == fib2) {
                fibonacciNumbers.add(nums[i]);
                i++;
            } else {
                int fib3 = fib1 + fib2;
                fib1 = fib2;
                fib2 = fib3;
            }
        }
        return fibonacciNumbers;
    }


    //Math Solution: Extra space O(1) Runtime O(n)
    List<Integer> getFibonacciNumbers(int[] nums) {
        List<Integer> fibonacciNumbers = new ArrayList<>();
        for(int n: nums) {
            if(isFibonacci(n)) fibonacciNumbers.add(n);
        }
        return fibonacciNumbers;
    }
    boolean isFibonacci(int n)
    {
        // n is Fibonacci if one of 5*n*n + 4 or 5*n*n - 4 or both
        // is a perfect square
        return isPerfectSquare(5*n*n + 4) ||
                isPerfectSquare(5*n*n - 4);
    }

    boolean isPerfectSquare(int x)
    {
        int s = (int) Math.sqrt(x);
        return (s*s == x);
    }

    class Location {
        int x;
        int y;
        //...
    }

    class Car {
        int id;
        Location location;
        //..
    }

    List<Car> nearestCars(Location user, List<Car> cars) {
        //max heap
        PriorityQueue<Car> pq = new PriorityQueue<>((car1, car2) -> (distance(car2.location, user) - distance(car1.location, user)));
        for(Car car: cars) {
            pq.add(car);
            if(pq.size() > 10) pq.poll();
        }
        return new ArrayList<Car>(pq);
    }

    int distance(Location car, Location user) {
        int dist;
        //put your own distance function here
        dist = (user.x - car.x) * (user.x - car.x) - (user.y - car.y) * (user.y - car.y);
        return dist;
    }

    void split(int[] array) throws Exception {
        int sum = 0;
        for(int n : array) sum += n;
        if(sum % 2 == 1) throw new Exception(); //impossible to split evenly
        List<Integer> firstPart = new ArrayList<>();
        List<Integer> secondPart = new ArrayList<>();
        if(!dfs(0, sum / 2, array, firstPart, secondPart)) throw new Exception(); //impossible to split evenly;
        //firstPart and secondPart have the grouped elements, print or return them if necessary.
    }

    boolean dfs(int i, int limit, int[] array, List<Integer> firstPart, List<Integer> secondPart) {
        if(limit == 0) {
            for(int j = i; j < array.length; j++) {
                secondPart.add(array[j]);
            }
            return true;
        }
        if(limit < 0 || i == array.length) {
            return false;
        }
        firstPart.add(array[i]);
        if(dfs(i + 1, limit - array[i], array, firstPart, secondPart)) return true;
        firstPart.remove(firstPart.size() - 1);

        secondPart.add(array[i]);
        if(dfs(i + 1, limit, array, firstPart, secondPart)) return true;
        secondPart.remove(secondPart.size() - 1);
        return false;
    }

    int[] kConsecutiveElements(int[] array, int k, int n) {
        if(k > n) return null;
        for(int i = 0; i < k; i++) {
            n -= array[i];
        }
        if(n == 0) {
            return Arrays.copyOfRange(array, 0, k - 1);
        }
        for(int i = k; i < array.length; i++) {
            n += array[i - k];
            n -= array[i];
            if(n == 0) {
                return Arrays.copyOfRange(array, i, i + k - 1);
            }
        }
        return null;
    }

    int rodCutGreedy(int rod, int[] prices) {
        int[] dp = new int[rod + 1];
        for(int r = 1; r <= rod; r++) {
            for(int cut = 1; cut < prices.length && cut <= r; cut++) {
                dp[r] = Math.max(dp[r], dp[r - cut] + prices[cut]);
            }
        }
        return dp[rod];
    }

//    //for even length, this returns the right node in linked list
//    Node findMid(Node head) {
//        Node fast = head, slow = head;
//        while(fast != null && fast.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return slow;
//    }
//
//    //for even length, this returns the left mid node
//    Node findMid(Node head) {
//        Node fast = head, slow = head;
//        while(fast != null && fast.next != null && fast.next.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return slow;
//    }

    void partitionZero(int[] arr) {
        int i = arr.length - 1, j = arr.length - 1;
        while(i >= 0) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            }
            i++;
        }
        for(; j >= 0; j--) {
            arr[j] = 0;
        }
    }


//
//    Node getSuccessor(Node root, int predecessor, Node successor) {
//        Node n = root;
//        while(n != null && n.val != predecessor) {
//            if(n.val > predecessor) {
//                successor = n;
//                n = n.left;
//            } else {
//                n = n.right;
//            }
//        }
//        if(n == null || n.right == null) return successor;
//        successor = n.right;
//        while(successor.left != null) {
//            successor = successor.left;
//        }
//        return n;
//    }
//
//
//    List<Node> topologicalSort(List<Node> graph) {
//        List<Node> order = new ArrayList<>();
//        Set<Node> visited = new HashSet<>();
//        for(Node n: graph) {
//            dfs(n, visited, order);
//        }
//        return order;
//    }
//
//    void dfs(Node n, Set<Node> visited, List<Node> order) {
//        if(visited.contains(n)) {
//            return;
//        }
//        visited.add(n);
//        for(Node c: n.children){
//            dfs(c, visited, order);
//        }
//        order.add(n);
//    }

    boolean isAnagram(String s1, String s2){
        if(s1.length() != s2.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for(Character c: s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(Character c: s2.toCharArray()) {
            if(!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if(map.get(c) == 0) map.remove(c);
        }
        return true;
    }



//    static int maxSubtreeSum = 0;
//    static Node maxSumSubtree = null;
//    static int subtreeSum(Node root) {
//        if(root == null) return 0;
//
//        int sum = root.val + subtreeSum(root.left) + subtreeSum(root.right);
//        if(sum > maxSubtreeSum) {
//            maxSubtreeSum = sum;
//            maxSumSubtree = root;
//        }
//        return sum;
//    }


    class Node {
        int val;
        List<Node> children;
    }




}

