package Google;

/**
 *
 * Google
 There is 1 meter walkroad. Randomly generate raindrops across the 1-meter range. Each drop is 1 cm.
 Find out how many rain drops does it take to cover all the 1 meter [-0.01~1]


Design structure，that supports slidingwindow avg, global avg, update(insert) value; in O(1) time

Tiny url - Focus on scalability and durability
 API
 Application Layer - how the user is gonna interact with the service tiny-long, long-tiny

 Persistence Layer -

     client -> through REST - Load Balancer
                               \    \     \
                   zookeeper   w1   w2     w3     cache(memcache/redis)
                               \ \   \   \   \
                                db  db     db

 chars in tiny url
 a-z
 A-Z
 0-9

 if tiny is 7 chars long , 62^7 = 3.5 trillion combinations - 1000/s urls generated -> 100 years to exhaust
                                                            - 1000,000/s urls generated -> 40 years to exhaust

 Assume 1000 new  urls / sec

 Any number between 0 - 3.5 trillion - 43bits


 Storage:
 Structure [key (tiny) : value(long)]

 Approach 1
 worker try get(random tiny) from db, if not exist than create mapping and store
 however 2 workers who created same random tiny and check for exist concurrently will cause 1 of the long url to corrupt

 Approach 2
 put_if_absent(tiny, big) deal with put in db layer
 NoSQL may or may not support put_if_absent but more scalable, cheap
 RDBMS supports put_if_absent but less scalable, expensive

 Approach 3
 worker generates (tiny, big) and put into db,
 then try to get(tiny) and see if big matches what was put
 if failed, generate new tiny for big



 URL generation:
 Approach1 MD5(128bit) hashing for long url  and take first 43bits
            saves space for the case when 2 users want to generate tiny url for the same long url

 Approach2 random generation

 Approach3 counter

 1. single host maintain a counter in the single host or install zookeeper (drawback: single point failure, high traffic load)

 2. all hosts maintain counter
 assume there are 64 hosts, need 6 bits to differentiate hosts ID.
 timestamp 32 bits
 6 + 32 + 5 = 43bits
 can have 5 bits of random or incremental value, 5bits is 32 unique numbers,

 If 1000requests/s, collision prob is high. because every host has 20 requests/sec

 getting 20 random numbers among 32 numbers per sec has high chance of collision

 if just incrementing the count in the 5 bits, it will also collide if there are 32 requests/sec

 if add/remove host, 6 bits to represent host wouldn't be enough

 Solution: increase randomness in counter and only take part of the time stamp

 3. Range based
 for now only worry about the 1st billion of the 3.5 trillion for now
 divide 1bill/1000 = 1million    1000 ranges each with 1 million

 maintained by zookeeper,each worker requests an unused range from zookeeper
 no collision will happen cuz each worker has its own range
 once a range is finished, worker can go into zookeeper and request for another range

drawback security thread if urls generated in sequential manner
 need to add random bits to the end of generated url 10 bits



 Cache layer
 why cache?
 the nature of the tiny url is that it gets access round time when its generated. The reads decay over time


 To make the read faster and more available across all countries, have CDNs in each area.
 A content delivery network (CDN) is a system of distributed servers (network) that deliver webpages and other Web content to a user based on the geographic locations of the user, the origin of the webpage and a content delivery server.







 FB

 find median across multiple machines.



 User groups - If I made a certain post visible to a user group, how to store the post? how to push the post to the
 * chosen group. If there are new friends who aren't grouped just yet, how to make them as a group?
 * DP sharding is also involved.
 *
 * tiny url
 *
 *

 */
public class RainDrop {

    static class Interval {
        double left = 0, right = 0.01;
        boolean isWet() {
            return left >= right;
        }
    }

    public static void main(String[] args) {
        simulateRainDrop();
    }

    public static void simulateRainDrop() {

        Interval[] sidewalk = new Interval[100];
        for (int i = 0; i < 100; i++) {
            sidewalk[i] = new Interval();
        }
        int cnt = 0, wetCnt = 0;
        while (wetCnt < 100) {
            ++cnt;
            double p = Math.random();
            double left = p - 0.005;
            double right = p + 0.005;
            if (left >= 0) {
                int idx = (int) (left / 0.01);
                if (!sidewalk[idx].isWet()) {
                    double iright = left - idx * 0.01;  //update seg[i].right with left bound of rain
                    if (iright < sidewalk[idx].right) {
                        sidewalk[idx].right = iright;
                        if (sidewalk[idx].isWet())
                            ++wetCnt;
                    }
                }
            }
            if (right <= 1) {
                int idx = (int) (right / 0.01);
                if (!sidewalk[idx].isWet()) {
                    double ileft = right - idx * 0.01;//update seg[i + 1].left with right bound of rain
                    if (ileft > sidewalk[idx].left) {
                        sidewalk[idx].left = ileft;
                        if (sidewalk[idx].isWet())
                            ++wetCnt;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
