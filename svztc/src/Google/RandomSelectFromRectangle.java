package Google;

/**

 帮朋友发帖，六月初onsite。第一轮：leetcode 10 https://leetcode.com/problems/re ... ching/#/description
 第二轮：第一问：二维坐标中给了一个矩形，要求生成一个任意一个坐标点，位置在矩形内。第二问，二维坐标中有多个不重叠的矩形，要求生成一个任意坐标点，位置在这些矩形中，要求生成的点落在各矩形的概率相同。
 followup：如果提供这两个function，isOverlap(rectangle a, rectangle b) 判断两个矩形是否重合, split(rectangle a, rectangle b) 若两矩形重合，将两个矩形分成互补重叠的小矩形， 问题是: 如何在上述的矩形中加入一个新的矩形。
 第三轮：给定一个0-1 matrix，0表示wall，1表示可以走，问从第一行是否能够找到一条path到达最后一行。followup 1：打印出最短的path。followup 2：如果不是0-1 matrix，0依旧表示wall，而>=1表示经过改点的cost，要求打印出从第一行走到最后一行cost最小的path
 第四轮：写一个表示employment的class，其中有三个method: makeManager(p1, p2): 将p1安排为p2的manager, makeColleague(p1, p2): build p1和p2为同事关系, isManager(p1, p2): 判断p1是否为p2的manager
 第五轮：给出一组同义词的mapping关系，比如：(fast, quick), (fast, speedy), (learn, study)表示fast==quick, fast==speedy, learn==study, 但是fast!=quick. 要求写一个function判断两个senten是否为同义关系。IsSynonymous(List<List<Strings>> synonymousWords, Strings sentence1, Strings sentence2). followup:如果同义关系可以传递，比如：speedy==quick， 修改上述function。.

 补充内容 (2017-6-22 13:59):
 第五轮：给出一组同义词的mapping关系，比如：(fast, quick), (fast, speedy), (learn, study)表示fast==quick, fast==speedy, learn==study, 但是quick!=speedy. 要求写一个function判断两个senten是否为...


 Google on-site June
Round 1
 Leetcode 10

Round 2
 Select a random point uniformly within a rectangle, (The side of rectangle is parallel to the x/ y grid).

 Follow-up: Given multiple non-overlapped rectangles on the 2D grid, uniformly select a random point from the rectangles.

Round 3
 Given a matrix of 0s and 1s where 0 is wall and 1 is pathway, print the shortest path from the first row to the last row.
 Can walk to the left, top, right, bottom at any given spot.

 Follow-up:
    If every pathway takes a cost (positive integer) to get through, print the minimum cost path from the first row to the last row.

 Round 4:
    Implement a class Employment with these 3 methods: assignManager(p1, p2): assign p1 as p2's manager. beColleague(p1, p2): make p1 and p2 peer colleagues. isManager((p1, p2): decide if p1 is the manager of p2.

 Round 5:
    Given a set of synonyms such as (fast, quick), (fast, speedy), (learn, study), decides if two sentences were synonymous.
    (The sentences were structurally the same and has the same number of words in them.
    The synonymous relation [fast ~ quick] and [fast ~ speedy] does not necessarily mean [quick ~ speedy].)

    Follow-up:
    If the synonymous relation passes down that [fast ~ quick] and [fast ~ speedy] implies [quick ~ speedy], decide if two sentences were synonymous.
 */
import java.util.*;
public class RandomSelectFromRectangle {

    class Rectangle {
        int x1, x2, y1, y2; //top left (x1, y1), bottom right (x2, y2)
    }

    class Point {
        int x, y;
        public Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    final Random rand = new Random();

    //Round2
    public Point randomSelectFrom(Rectangle rectangle) {
        return new Point(rectangle.x1 + rand.nextInt(rectangle.x2 - rectangle.x1 + 1),
                        rectangle.y2 + rand.nextInt(rectangle.y1 - rectangle.y2 + 1));
    }

    //Round2 follow-up
    //first of all decide which rectangle yields the point (randomly select a rectangle based on area as the weight)
    //then apply randomSelectFrom(rectangle) on the selected rectangle
    public Point randomSelectFrom(Rectangle[] rectangles) {
        int selected = -1, total = 0;
        for(int i = 0; i < rectangles.length; i++) {
            int area = (rectangles[i].x2 - rectangles[i].x1) * (rectangles[i].y1 - rectangles[i].y2);
            if(rand.nextInt(total + area) >= total) {
                selected = i;
            }
            total += area;
        }
        return randomSelectFrom(rectangles[selected]);
    }
}
