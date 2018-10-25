package FB;

import java.util.*;

class TaskCount {
    char task;
    int count;

    public TaskCount(char t, int c) {
        task = t;
        count = c;
    }
}


public class TaskSchedule {

    public static void main(String[] args) {

        TaskSchedule t = new TaskSchedule();
        t.schedule("AAABBB",2);

    }

    public void schedule(String tasks, int cd) {

        Map<Character, Integer> map = new HashMap<>();

        for(char task: tasks.toCharArray()) {

            map.put(task, map.getOrDefault(task, 0) + 1);

        }

        System.out.println(map);

        Set<TaskCount> set = new TreeSet<>(new Comparator<TaskCount>() {

            public int compare(TaskCount t, TaskCount o) {

                if(o.count != t.count) {

                    return o.count - t.count;

                }

                return t.task - o.task;
            }

        });

        for(char c: map.keySet()) {

            TaskCount tc = new TaskCount(c, map.get(c));

            set.add(tc);

        }



        while(!set.isEmpty()) {

            Iterator<TaskCount> iter = set.iterator();

            List<TaskCount> toRemove = new ArrayList<>();  //to avoid concurrent modification

            for(int i = 0; i <= cd && !set.isEmpty(); i++) {

                if(iter.hasNext()) {

                    TaskCount curr = iter.next();

                    System.out.print(curr.task);

                    curr.count--;

                    if(curr.count == 0) toRemove.add(curr);

                } else {

                    System.out.print("_");

                }

            }

            for(TaskCount tc: toRemove) {

                set.remove(tc);

            }


        }


    }

}
