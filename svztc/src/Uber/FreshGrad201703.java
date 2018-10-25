package Uber;

/**
 1. manager - behavior questions, 问了一些浅显的system design比如他们architecture是怎样的之类的 以及publish/subscribe model...因为我作死突然讲到他们architecture之类的..
 2. bar raiser - behavior questions, 很简单的coding，要你找出一坨meeting是否overlap，meeting有starttime endtime, 然后表示format自己定，然后要求O（n），meeting全部限制在一天内，并且时间都是用minute表示。就把一天分割成60*60*24个element就可以了
 3. coding - subset sum,followup问的是如何改进，我dp实在没时间写完，在白板上笔画了一下...
 4. coding - 这道题真的就有毒了...input是2D array，里面放着要么'/'要么'\'，问这个rectangle（2D）被图像性的分成了几块...比如
 /\
 \/
 就是被分成了5块 （2D array）我struggle了很久不停和他说话，最后给了提示是把每个cell分成两块来判断，比如‘\’这一块左边那块只能往左和下扩张，右边那块只能有和上扩张，然后类似用number of islands的做法DFS写出来。等我拿到hint给他讲完我的想法之后只剩15分钟...很多种情况要讨论所以刚写完code就没时间了...卒
 5. design excel，都是地里那几个点
 我肯定是跪在第四个上的...可能manager面我的时候那些浅显的system design讲的不是很好也导致了悲剧。值得注意的是我schedule上明确提到最后一轮一定是design，所以逃不过的

 1. Behavioral questions. Basic system design concepts. Publish/subscribe model. Discussion on Uber architecture.
 2. Behavioral questions. Coding: Find if a set of meetings overlap. Meeting has a starttime and an endtime with accuracy to minute. All meetings take place in the same day. Do this in O(n) time.
 3. Coding: Subset sum. Follow-up: Optimize the solution.
 4. Coding: Given a 2D array of either '\' or '/', find out how many pieces this rectangle is divided into graphically.
    For a 2X2 matrix with  /\
                           \/
    it splits into 5 pieces - the diamond in middle and the four corners. Return 5 as the answer.
 5. Design Excel.



 FB messenger

 I. Feature/Requirement
    多少user?       300M users
    多少message?    10B message/day
    average num of characters per message? 160 characters
    1-1 only? group chat?  1-1
    attachment? no
    Max size of message? 64KB
    notification? no
    user presence? (user can mark themselves active or not)  no


 II.: Constrain
    Data storage: 10B*160Byte = 1.6TB/day
    If we want to store message for 10 years: 1.6*365*10 = 6PB storage
    Traffic: 10B/24/60/60 = 115K message/s
    Latency matters? Yes
    A or C? C
        Consistency: You customers, once they have updated information with you, will always get the most updated information when they call subsequently. No matter how quickly they call back
        Availability: Remembrance Inc will always be available for calls until any one of you(you or your wife) report to work.
        Partition Tolerance: Remembrance Inc will work even if there is a communication loss between you and your wife!

 Step3: 算一下你需要多大的machine多少台
    Need 6PB storage space, assume a machine has 10TB space (这里指storage 不是 RAM 因为不同于cache)
                    (different from designing a cache. if cache RAM demands 30TB, each machine is 4 core & 72GB RAM, need 420 machines, QPS 10M/420/4 = 6000
                       6000 QPS per core. 1/6000 = 167us per query.  )
    6PB/10TB = 600台, 115K/600 = 191 QPS簡直輕鬆愉快


 Step4: architecture
    既然這是messenger 那就勢必要design server的api
    視情況可能還要對不同的client device設計不同的api

    SendMessenge(senderId, receiverId, content)
    getConversationByUserId(userid, page number, pagesize, latest updated timestamp)
    getMessengeByConversationId(userId, page number, pagesize, latest updated timestamp)

    1.這時候一定要提一下網路世界裡面很容易出現的問題 就是先送的封包後到或是後送的封包先到 所以在server端要記錄每個messenger的timestamp
    這樣別人在fetch messenge的時候才不會出現錯誤的順序 可是這並不需要當成SendMessenge的input 因為前端不可信任(可能有惡意的前端改變你messenge的順序)
    2.pagesize, pagenumber就是可以適用在不同的前端(不同螢幕大小 解析度) 所以前端要自己算這一次fetch要跟server要多少conversations/messenge 後兩個api 其實很像 但都不可或缺
    3.給latest updated timestamp這樣同樣的message不用每次都重複傳到前端 前端只需要request user上次fetch之後的messg就好了


 Step5: 喇賽時間
    現在開始處理scaling問題 上面那張圖只有一個Application Server 要是掛了怎麼辦
    迷途書僮: 輕鬆 用多個application 處理 每個application都是stateless 要是問某個AS沒回應在問下一個

    這樣太浪費時間了 而且client的logic太過複雜
    迷途書僮: 所以加一層load balancer 就讓Client always 問LB 讓LB去maintain哪台AS還活著哪台掛了 Client不應該有任何Server端的資訊

    這個diagram還是會有single point of failure的問題 那怎麼辦呢
    迷途書僮: 我有認真看J大的文章 Active-Active LB 搞定！

    這樣你已經處理好AS端availablity的問題了 那接下來換Data Layer 你選RDBM還是NoSQL
    迷途書僮: 剛剛算過要很多台機器 才存的下所有messenger 而且messenger是個write heavy的service, RMDB會非常難做 更不用說需要sharding,
    這樣每次join都需要從不同的data server拿table來join 會崩潰 所以我們選擇denormalized NoSQL

    好 哪種NoSQL比較好呢
    迷途書僮: 下一篇jyt0532的救世文會告訴你各個NoSQL的比較 我先告訴你答案 支援write heavy的NoSQL不是Cassandra就是HBase 還有Amazon DynamoDB
    事實上FB一開始是用Cassandra (column indexes)後面改成HBase

    用user_id來當index sharding, Consistent hash!
    https://www.facebook.com/notes/facebook-engineering/the-underlying-technology-of-messages/454991608919/

    面試官: 那如果單台DB掛了怎麼辦
    迷途書僮: replication! consistent hash搭配replication的方法

    面試官: 好已經解決single point of failure的問題了 可是現在performance太差 怎麼辦
    迷途書僮: Cache 但我們這裡要求要strong consistency 所以選擇write through方式

    面試官: 非常好 還有個concurrency的問題 DB level通常這些常用的nosql都有implement atomic write(Cassandra, HBase) 可是你的cache layer沒有
    迷途書僮: 只好忍痛加個user level lock 一次只有一個thread可以寫入我的cache

 https://www.facebook.com/notes/facebook-engineering/inside-facebook-messages-application-server/10150162742108920/

 */
public class FreshGrad201703 {

    //2
    public boolean meetingOverlap(int[][] meetings) {
        boolean[] schedule = new boolean[24 * 60];
        for(int[] time:meetings) {
            for(int i = time[0]; i <= time[1]; i++) {
                if(schedule[i]) return true;
                schedule[i] = true;
            }
        }
        return false;
    }

    //4
    public int segmentCount(char[][] m) {
        int len = m[0].length;
        boolean[] upperHalf = new boolean[m.length * len];
        boolean[] lowerHalf = new boolean[m.length * len];

        int count = 0;
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < len; j++) {
                if(!upperHalf[i*len + j]) {
                    count++;
                    dfs(m, upperHalf, lowerHalf, i, j, 0);
                }
                if(!lowerHalf[i*len + j]) {
                    count++;
                    dfs(m, upperHalf, lowerHalf, i, j, 1);
                }
            }
        }
        return count;
    }
    //upper:0, lower:1, left:2, right:3
    private void dfs(char[][] m, boolean[] upperHalf, boolean[] lowerHalf, int x, int y, int position) {
        if(x < 0 || x == m.length || y == m[0].length || y < 0) {
            return;
        }
        if((position == 2 && m[x][y] == '\\') || (position == 3 && m[x][y] == '/')) position = 1;
        if((position == 2 && m[x][y] == '/') || position == 3 && m[x][y] == '\\') position = 0;
        int id = x * m[0].length + y;
        if((position == 0 && upperHalf[id]) || (position == 1 && lowerHalf[id])) { //if visited
            return;
        }
        if(position == 0) upperHalf[id] = true;
        else lowerHalf[id] = true;
        if(position == 0 && m[x][y] == '\\') {
            dfs(m, upperHalf, lowerHalf, x, y + 1, 2); //go right
            dfs(m, upperHalf, lowerHalf, x - 1, y, 1); //go up
        }
        if(position == 0 && m[x][y] == '/') {
            dfs(m, upperHalf, lowerHalf, x, y - 1, 3); //go left
            dfs(m, upperHalf, lowerHalf, x - 1, y, 1); //go up
        }
        if(position == 1 && m[x][y] == '\\') {
            dfs(m, upperHalf, lowerHalf, x, y - 1, 3); //go left
            dfs(m, upperHalf, lowerHalf, x + 1, y, 0); //go down
        }
        if(position == 1 && m[x][y] == '/') {
            dfs(m, upperHalf, lowerHalf, x, y + 1, 2); //go right
            dfs(m, upperHalf, lowerHalf, x + 1, y, 0); //go down
        }
    }

}
/*
   \\\/\/
 */
