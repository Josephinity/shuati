package WorkUndone;

/**
 *
 * Master Degree 2 year exp,
 *
 *
 * 1. Behavioral - most challenging project.  tech - bit manipulation
 *
 * 2. A matrix has 0s and 1s only.. All the 1s in every row are to the of 0s. Find out the leftmost 1 in the entire matrix.
 *
 * 3. A dp problem similar to coins changing.
 * Find out how many ways you can make change to N cents given coins of values [a1, a2, a3...], each type of coin got infinite supplies.
 * Arrangement of coins don't matter.
 *
 * 4. Design news feed.
 * A user wrote a post with the basic user info, pictures and time etc (I even drafted a basic UI on whiteboard).
 * How to obtain these information and store them.
 * If a new feature with a button is added. How to maintain availability of old service to users who did not update the app.
 * Did not dive further into scaling.
 *
 *
 *5.
 * User grouping
 * User groups - If I made a certain post visible to a user group, how to store the post? how to push the post to the
 * chosen group. If there are new friends who aren't assigned to any group just yet, how to treat them as if they were in an
 * 'unassigned' group?
 * DB sharding is involved.
 *
 *
 *
 *
 1. 一个美国帅哥+一个shadow。BQ 最有挑战的项目。+一道bit manipulation题
 2. 年轻好动小哥， matrix每一行都是00111这一类的，只要出现1，后面全是1，找整个最左边的1.
 3.国人好好小哥，一个标准dp问题，有点像coins change那道。问了time complexity,还有另一道题想不起来了。
 4.AB印哥哥，Design news feed一类的延伸吧，问了很多问题一个接一个。有点招架不住的感觉。

 news feed就是问 - 比如这些是用户写的一条post，有用户基本信息，内容，图片，时间等等（当时还画了一下基本ui），如何拿到这些信息，如何存储.
 还问了如果有新的feature，比如加了一个button，如何让没有update app的人仍然流畅使用他们现有的。scale倒是没细问。时间有点长了也记不起来剩下问了什么了。。
 用户分组 - 比如我这条post选了指定分组可见。如何存储。如何推送给指定分组。如果有新的朋友没有分组，怎么给他们显示一类的。还问了db sharding神马的。

 大概就说了一下存的时候存一个column是分组，每次pull news的时候把朋友名单拿出来，再确定是不是分组里面的。
 其实我个人感觉系统设计没有正确答案，如果把情况都考虑到，能说得通，就可以了。
 因为边design你自己会面临很多小的选择。边说边看面试官的反应，我自己design这方面也比较弱。
 说的时候不太有信心。问了很多次“这样可以吗？”微笑小哥就点点头，或者说-你决定吧。我就硬着头皮说，最后他说嗯差不多。
 顺着我的思路问了一些小细节。比如要不要cache或者db sharding 的key是什么啦。


 5.年轻微笑小哥。Design round，用户分组一类的设计。
 */
public class May2017A {
}
