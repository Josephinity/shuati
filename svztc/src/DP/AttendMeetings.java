package DP;
import java.util.*;
/**
 weighted meeting room 20m
 You are given a series of unsorted meetings. You need to schedule them.
 So no two meetings can be overlapped. Goal is to find maximum weight subset of mutually non-overlap meetings.
*/

/* test case

        AttendMeetings am = new AttendMeetings();

        List<Meeting> meetings = new ArrayList<>();

        meetings.add(new Meeting(0,6,5));
        meetings.add(new Meeting(1,4,6));
        meetings.add(new Meeting(3,5,12));
        meetings.add(new Meeting(3,8,9));
        meetings.add(new Meeting(4,7,2));
        meetings.add(new Meeting(5,9,3));
        meetings.add(new Meeting(6,10,1));
        meetings.add(new Meeting(8,11,4));



        System.out.println(        am.maxWeightMeetingSchedule2(meetings));
        System.out.println(        am.maxWeightMeetingSchedule(meetings));
 */
 class Meeting{
    int startTime;
    int endTime;
    int weight;

    public Meeting(int s, int e, int w) {
        startTime = s;
        endTime = e;
        weight = w;
    }
 }

public class AttendMeetings {


    //recursive memorization
    public int maxWeightMeetingSchedule2(List<Meeting> meetings) {
        Collections.sort(meetings, (Meeting m1, Meeting m2) -> (m1.endTime - m2.endTime)); //sort meetings by end time ascend
        int[] mem = new int[meetings.size()];
        return maxWeightMeetingScheduleRec(meetings, mem, meetings.size() - 1);
    }

    private int maxWeightMeetingScheduleRec(List<Meeting> meetings, int[] mem, int k) {

        if(k < 0) return 0;

        if(mem[k] > 0) return mem[k];

        //case1: keep meetings[k], then skip all the meetings that overlap with k
        int j = k - 1;
        // or binary search
        while(j >= 0 && meetings.get(j).endTime > meetings.get(k).startTime) j--;

        int case1 = meetings.get(k).weight + maxWeightMeetingScheduleRec(meetings, mem, j);

        //case2; not keep meeting k
        int case2 = maxWeightMeetingScheduleRec(meetings, mem, k-1);
        mem[k] =  Math.max(case1, case2);

        return mem[k];
    }



    //dp bottom-up
    public int maxWeightMeetingSchedule(List<Meeting> meetings) {

        Collections.sort(meetings, (Meeting m1, Meeting m2) -> (m1.endTime - m2.endTime)); //sort meetings by end time ascend

        int[] maxWeight = new int[meetings.size() + 1];

        for(int k = 1; k < maxWeight.length; k++) {

            //case1: keep meetings[k], then skip all the meetings that overlap with k
            int j = k - 1;
            // or binary search
            while (j >= 0 && meetings.get(j).endTime > meetings.get(k).startTime) { //meeting[j] overlaps meeting[k]
                j--;
            }
            int case1 = meetings.get(k - 1).weight + maxWeight[j + 1];

            //case2; not keep meeting k
            int case2 = maxWeight[k - 1];
            maxWeight[k] = Math.max(case1, case2);

            for(int w:maxWeight)
                System.out.print(" "+w);
            System.out.println();
        }

        return maxWeight[meetings.size()];
    }


}
