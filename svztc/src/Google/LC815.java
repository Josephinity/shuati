package Google;

/**
LC815 BFS

 A = a+b-c-a-b-c-a-b (Tree)
 B = b+a+c+a+b-c+b (Tree)
 Is A equal to B



 Count Number Given Array
 Level 1. Unsorted Array [1, 3, 2, 1, 10, 2 ,3 , 4]
 Find 1
 Level2 . Sorted Array Question
 Find 1




 Maze
 N,M array
 Level 1 is 0,0 to N-1,M-1 => is Path exsits?
 Level 2 is => is path exists then print path
 Level 3 is => is path exists then min cost path
 Level 4 is => O(nm log mn) using Priority Queue.


 HTML Reverser
 <A>(hello)(<P>ab</P>)(<S>hi</S>)</A> => <A>(<S>ih</S>)(<P>ba</P>)(olleh)</A>


 */
import java.util.Set;
import java.util.HashSet;
public class LC815 {

    public static void main(String[] args) {
        numBusesToDestination(new int[][]{{1,2,7},{3,6,7}}, 1, 6);
    }

    public static int numBusesToDestination(int[][] routes, int S, int T) {
        if(S == T) return 0;
        boolean[] visited = new boolean[routes.length];
        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> stops = new HashSet<>();
        stops.add(S);
        visitedStops.add(S);
        int rides = 0;
        while(!stops.isEmpty()) {
            rides++;
            Set<Integer> nextStops = new HashSet<>();
            for(int i = 0; i < routes.length; i++) {
                if(!visited[i]) {
                    for(int stop: routes[i]) {
                        if(stops.contains(stop)) {
                            for(int s: routes[i]) {
                                if(s == T) return rides;
                                if(!visitedStops.contains(s)) {
                                    nextStops.add(s);
                                }
                            }
                            visited[i] = true;
                            visitedStops.addAll(nextStops);
                            break;
                        }
                    }
                }
            }
            stops = nextStops;
        }
        return -1;
    }
}
