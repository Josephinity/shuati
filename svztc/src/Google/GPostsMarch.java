package Google;

/**
 dp题。给一个grid的宽和长，求得从左下的点到右下的点所有可能的路径之和。
 起点当然是左下，要求每次只能走三个方向， ➡↗↘
 follow-up: 2d dp to 1d dp

 DP Problem. Given the length and width of a matrix, get the number of paths from bottom-left to bottom right.
 You may only walk into those 3 directions ➡ (right) ↗ (upper-right) ↘ (lower-right) at each point.
 Follow-up: optimize 2d DP to 1d DP of linear extra space.


 给一个公式2^i*5^j
 给一个上限N，求得所有 可能的数(i >= 0, j >= 0) 并且increasing order输出
 Print all numbers satisfying the expression 2^i * 5^i (where i, j are integers i >= 0 and j >= 0) in increasing order up to a given bound N.


 有个token stream，每个token有token value和time stamp，输出10秒内没有出现过的token value；follow-up，
 如果未来10秒内会出现相同的token value，则即使当前的token value满足条件也不能输出。
 Process a token stream with each token carrying a token value and a timestamp. Only output the values that didn't show up in the past 10 seconds.
 Follow-up: An additional rule for the filter: output a token only if a duplicated token value does not show up within 10 seconds in the future as well as from the past of
 the token's timestamp.


 In a complete tree of size N, tree nodes are labeled from 1 to N. Labels are assigned level by level from left to right.
 Given N and two labels A and B denoting two nodes in the tree, find the label for lowest common ancestor of A and B.
 A, B, N are integers. This may not be a binary tree.


 System Design
 Availability test/debug on distributed system. Discussed and drafted about failover, replication, NoSQL etc.
 Interviewer seemed to be expecting more but time ran out.



 */
public class GPostsMarch {

}
