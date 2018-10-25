package Uber;

/**
 题目是RateLimit，允许每个客户在每秒内访问100次，bool isAllowed(int clientID)
 我说那我用Token Bucket吧


 Google On-site

 MTV onsite.
 1. 有n路公交线，已知每路公交的经停车站，公交是bi-direction，问从车站A到车站B最少需要几次换乘。
 2. 设计一个class存储股票价格。支持update某个timestamp的价格。query目前价格以及历史最高价格。
 3. 设计一个class log process执行顺序。每个process都会start和finish，当一个process finish的时候才可以写入log，但是log必须按照时间顺序排好，先start的要写在前面。
 4. 给一个word list，有一个char stream, 如果stream中出现list中的word要return true。
 5. 乐扣散久久。
 \第二轮先问了hashmap如何实现。写完后讨论每个操作的复杂度，如何优化。
 第三轮followup是多线程下是否thread safe。
 第四轮先自我介绍聊了简历。
 第五轮讨论用dfs bfs的优缺点，选择的原因。



 DropBox

 Dropbox 和HR 是回复邮件最及时的 告诉结果也是最快的 都是一周内肯定给结果
 他家的伙食 也是湾区食堂最好吃的 没有之一

 Phone：
 leetcode: game of life
 如果board 非常大怎么办？怎么样来存到disk里面？用bit
 用了bit以后，怎么样来解这个题呢？
 一行一行读进去，然后没处理好一行，就写出去。


 Onsite： Congrats to Brian
 第一轮：
 给一个Array of byte，给你一个file name，问这个array of byte是不是包含在file里面。
 用rolling hash做.

 第二轮：
 给一个Iterator，里面会有photo iD，让你找出来被hit 最多的Top K的photo ID
 follow up：
 如果说这个iterator 可能会不断的增加东西，比如说现在已经iterate到了end，但是10分钟以后，又有新的东西加入到了iterator里面，怎么样修改我算法？

 午饭非常非常非常好吃
 然后是一轮Demo，讲了一下Dropbox Paper
 第三轮：
 写一个类似网址爬虫，找出来这个网站能触及到的所有子网站
 他给你：vector<string> getURLs(string url); 这个method
 问DFS 和BFS的优劣
 follow up：
 写一个多线程的

 第四轮：
 写一个ID allocator，就是比如说给你闲置一个最大的allocator ID： MAX，然后你从0还是给他ID一直到MAX
 User release ID的时候，你需要处理。.
 也需要处理exception
 follow up就是加速，优化空间
 最优解：segment tree + bit.
 */
//A simple rate limiter with integer timestamp and integer userID
public class RateLimiter {

}
