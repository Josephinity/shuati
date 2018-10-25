package Wish;

/**
 Rank N people in a game. There may be a tie among participants. Find the number of possible ways of ranking outcome.
 */
import java.util.List;
import java.util.ArrayList;
public class RankCombinations {

    /*
    dp relation:
    With N participants [p1, p2, p3... pN], one ranking could be:
    1st place: p1
    2nd place: p2, p3
    ...
    xth place: pN

    In this case, if one more player pL joins the game. The new player pL can be placed into

                        pL can be here as new 1st place
    1st place p1        pL can be here tied with 1st place
                        pL can be here as new 2nd place
    2nd place p2, p3    pL can be here tied 2nd place
    ...                 ...
    xth place pN        pL can be here tied last place
                        pL can be here as new last

    So for every rank combination of N people, adding 1 more player creates rowNumber * 2 + 1 ways of ranking.
    So if we keep track of the row numbers of all previous combinations of ranking,
    a ranking with R rows is gonna derive into R + 1 more cases (with R + 1 row numbers) plus R more cases (with R row numbers).


    */
    public static void main(String[] args) {
        System.out.println(rankCombinations(4));
    }

    public static int rankWays(int n) {
        int[] base = new int[2]; // start from case for 0 participants
        base[1] = 1;

        for(int p = 2; p <= n; p++) {
            int[] ways = new int[p + 1]; //index is row number, value is count of ways
            for(int r = 1; r < base.length; r++) {
                ways[r + 1] += base[r] * (r + 1);
                ways[r] += base[r] * r;
            }
            base = ways;
        }
        int sum = 0;
        for(int x: base) sum += x;
        return sum;
    }

    public static List<List<List<Integer>>> rankCombinations(int n) {
        List<List<List<Integer>>> base = new ArrayList<>();
        base.add(new ArrayList<>());
        base.get(0).add(new ArrayList<>());
        base.get(0).get(0).add(1);
        for(int p = 2; p <= n; p++) {
            List<List<List<Integer>>> combinations = new ArrayList<>();
            for(List<List<Integer>> comb: base) {
                for(int i = 0; i <= comb.size(); i++) {
                    List<Integer> rank = new ArrayList<>();
                    rank.add(p);
                    List<List<Integer>> newComb = new ArrayList<>(comb);
                    newComb.add(i, rank);
                    combinations.add(newComb);
                }

                for(int i = 0; i < comb.size(); i++) {
                    List<List<Integer>> newComb = deepcopy(comb);
                    newComb.get(i).add(p);
                    combinations.add(newComb);
                }
            }
            base = combinations;
        }
        //print all ranking combinations. multi players in a same sublist makes a tie.
        for(List<List<Integer>> ranks: base) System.out.println(ranks);
        return base;
    }

    private static List<List<Integer>> deepcopy(List<List<Integer>> ls) {
        List<List<Integer>> copy = new ArrayList<>();
        for(List<Integer> sublist: ls) {
            copy.add(new ArrayList<Integer>(sublist));
        }
        return copy;
    }
}
