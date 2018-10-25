package FB;

/**
 *
 两周多前FB onsite.最后一轮coding.被问了一道没见过的题（可能是我做的题少所以没见过）,跟大家分享一下。
 题目是给你一个机器人和一个房间，你并不知道机器人在房间什么位置，你也不知道房间的形状大小，你有一个遥控器，可以让机器人走前后左右四个方向。
 这里给你一个move method : boolean move(int direction), direction: 0,1,2,3 表示四个方向。能移动就返回true,不能移动表示false。问你：这个房间多大。

 FB on-site last round of coding. (This is also in the internal question bank of G.
 There is a robot in a room. The initial location of the robot is unknown. The shape or area of the room is unknown.
 You have a remote that could walk the robot into any of the four directions - up, left, down or right.
 Here is the move method: boolean move(int direction), direction: 0, 1, 2, 3. If the robot hits the wall, the method returns false.
 Otherwise returns true and the robot moves on to the direction given.
 Find out the area of the room.


 e.g.
   X
 XXX  X
  XXXXX         'X' marks the floor plan of the room.

 A room like such has an area of 10.


 */

import java.util.*;

public class AreaOfRoom {

    //Robot robot;

    //......


    //x, y is the current location of the robot
    //from is the last step taken
    //map<x, set<y>> marks the places visited
    public int getArea(int x, int y, int from, Map<Integer, Set<Integer>> room) {

        if(room.containsKey(x) && room.get(x).contains(y)) { //if this place was visited

            //robot.move(moveBack(from));        //turn robot back

            return 0;              //add 0 to total area

        }


        //mark current place visited
        if(!room.containsKey(x)) room.put(x, new HashSet<>());
        room.get(x).add(y);

        int area = 1;
//
//        if(from != 0 && robot.move(moveBack(0))) {//if robot was not moved to current place from below, move robot down
//
//            area += getArea(x, y - 1, 0, room);
//
//        }
//
//
//        if(from != 1 && robot.move(moveBack(1))) {//if robot didn't come from the right, move it to the right
//
//            area += getArea(x + 1, y, 1, room);
//
//        }
//
//        if(from != 2 && robot.move(moveBack(2))) { //if robot didn't come from above, move it up
//
//            area += getArea(x, y + 1, 2, room);
//
//        }
//
//        if(from != 3 && robot.move(moveBack(3))) { //if robot didn't come from the left, move it to the left
//
//            area += getArea(x - 1, y, 2, room);
//
//        }
//
//        //after counting the areas around the current location, walk robot back to where it was from
//        robot.move(moveBack(from));

        return area;
    }


    private int moveBack(int from) {

        if(from == 0) return 2;
        if(from == 1) return 3;
        if(from == 2) return 0;
        return 1;

    }


}
