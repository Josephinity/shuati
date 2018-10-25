package WorkUndone;

/**
 第一轮，linkedlist,找出最大的两个, 然后swap，不是swap value，swap reference。
 第二轮，刚开始主要谈论了一下现在项目，然后写了一个topological sort。
 第三轮，RGB颜色转换比如现有#2f3d13，有16进制的00,33,66,99，cc, ff.要把现有的数字转成最close to这几个数字。比如#2f3d13 -> #333300；
 第二题是findNthsmallest element in BST
 第四轮，三道题啊。。。。1. one edit distance 2. wall, flower, matrix找到能看见最多flower的点，地里高频题 3. count of smaller number before itself
 第五轮， 给一个API，O(1)时间计算 slidingwindow avg, global avg, update(insert) value;
 第二题，DP， 有几座城市，每个月在每个城市都有不同的假期，然后每个城市有飞往不同城市的航班，求最大能获取的假期和Path. dp(i)(j) 代表第i个月在第j个城市所能获得的最大假期。
 DP 方程大概是 dp(i)(j) = Math.max(dp(i-1)(fromCity)+map(i)(j), dp(i)(j))
 */
public class March2016a {


}
