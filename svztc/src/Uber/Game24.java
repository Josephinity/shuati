package Uber;

/**
 *Solve a 24 game
 */
public class Game24 {

    public static void main(String[] args) {
        Game24 game = new Game24();
        game.solve(new int[]{12, 3, 2, 2});
    }

    public void solve(int[] cards) {
        boolean[] played = new boolean[4];
        for(int i = 0; i < 4; i++) {
            StringBuilder str = new StringBuilder(String.valueOf(cards[i]));
            played[i] = true;
            solve(cards, played, cards[i],str);
            played[i] = false;
        }
    }

    private void solve(int[] cards, boolean[] played, double res, StringBuilder str) {
        if(played[0] && played[1] && played[2] && played[3]) {
            if(res == 24)
                System.out.println(str.toString());
            return;
        }
        for(int i = 0; i < 4; i++) {
            int len = str.length();
            if(!played[i]) {
                played[i] = true;

                str.insert(0, '(');
                str.append("+" + cards[i] + ")");
                solve(cards, played, res + cards[i], str);

                str.setCharAt(len + 1, '-');
                solve(cards, played, res - cards[i], str);

                str.deleteCharAt(0);
                str.deleteCharAt(str.length() - 1);
                str.setCharAt(len, '*');
                solve(cards, played, res * cards[i], str);

                str.setCharAt(len, '/');
                solve(cards, played, 1.0 * res / cards[i], str);

                str.delete(len, str.length());
                played[i] = false;
            }
        }
    }
}
