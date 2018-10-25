package Graph; /**
 Given a list of dependencies, return a valid order to install the packages.
 Return None if there is no valid sequence.

 # Definition for a Directed graph node
 # class DirectedGraphNode:
 #     def __init__(self, x):
 #         self.label = x
 #         self.dependencies = []
 */
//class Solution:
//        def dfs(self, i, countrd, ans):
//        ans.append(i)
//        countrd[i] -= 1
//        for j in i.dependencies:
//        countrd[j] = countrd[j] - 1
//        if countrd[j] == 0:
//        self.dfs(j, countrd, ans)
//        """
//@param graph: A list of Directed graph node
//        @return: A list of integer
//        """
//        def topSort(self, graph):
//        # write your code here
//        countrd = {}
//        for x in graph:
//        countrd[x] = 0
//
//        for i in graph:
//        for j in i.dependencies:
//        countrd[j] = countrd[j] + 1
//
//        ans = []
//        for i in graph:
//        if countrd[i] == 0:
//        self.dfs(i, countrd, ans)
//        return ans


import java.util.*;

class Pack {
     int label;
     List<Pack> dependencies; // a list of prerequisite packages

     public Pack(int x) {
         label = x;
         dependencies = new ArrayList<>();
     }

     public void addDependency(Pack other) {
         dependencies.add(other);
     }
}

public class SeqOfPackInstallation {

     public static void main(String args[]) {
         List<Pack> packs = new ArrayList<Pack>();
         packs.add(new Pack(0));
         packs.add(new Pack(1));
         packs.add(new Pack(2));
         packs.add(new Pack(3));
         packs.get(0).dependencies.add(packs.get(2));
         packs.get(0).dependencies.add(packs.get(1));
         packs.get(2).dependencies.add(packs.get(1));
         packs.get(3).dependencies.add(packs.get(2));
         SeqOfPackInstallation sol = new SeqOfPackInstallation();
         List<Integer> seq = sol.getSequence(packs);
         System.out.println(seq);
     }

     public List<Integer> getSequence(List<Pack> packs) {

         List<Integer> sequence = new ArrayList<>();
         if(packs.isEmpty()) return sequence;

         Map<Integer, List<Integer>> map = reverseMapping(packs);


         int[] countPre = new int[packs.size()];
         for(Pack pack: packs) countPre[pack.label] = pack.dependencies.size();

         Queue<Integer> queue = new LinkedList<>();
         queue.poll();


         //start the dfs
         for(int i = 0; i < countPre.length; i++) {
             if(countPre[i] == 0) {                    // if pack[i] is naturally a sink
                 sequence.add(i);                      // install pack[i]
                 dfs(map, countPre, sequence, i);      //update countPre for pack[i]'s subsequents
             }
         }
         return sequence;
     }


     public void dfs(Map<Integer, List<Integer>> map, int[] countPre, List<Integer> sequence, int i) {
         if(map.containsKey(i)) {
             for(int curr: map.get(i)) {
                 countPre[curr]--;
                 if(countPre[curr] == 0) {     //when pack[curr] becomes a sink with the only remaining prerequisite pack[i] installed
                     sequence.add(curr);       //install new sink - pack[curr]
                     dfs(map, countPre, sequence, curr);   //having installed pack[curr], check whether pack[curr]'s subsequents are ready for installation
                     countPre[curr]--;         // avoid repetitive installation for pack[curr] in case curr > i
                 }
             }
         }
     }
     public Map<Integer, List<Integer>> reverseMapping(List<Pack> packs) {
         Map<Integer, List<Integer>> map = new HashMap<>();
         for(Pack pack: packs) {
             for(Pack pre: pack.dependencies) {
                 List<Integer> subsequent = map.getOrDefault(pre.label, new ArrayList<>());
                 subsequent.add(pack.label);
                 map.put(pre.label, subsequent);
             }
         }
         return map;
     }


}

