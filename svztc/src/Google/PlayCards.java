package Google;

/**
 一个扑克牌游戏，比如我手上有52张牌中的一些牌，可以用以下的方式打出：1 三张或四张数字相同，花色不同，可以一起打出。2 相同花色连续三张以上可以打出。其他任何组合都不可以打出。如果我手上有一些牌，问判断是否可以按照以上两种规则打出。面试时并没有什么太好的思路，用了dp。不知道是不是下午有些累了，写的有点慢，脑子有时候会卡壳，写了满满一面墙才勉强写完。最后没有时间继续讨论了，也没有follow up。也不知道有没有更好的方法。
 Card game rule: the hand is drawn from a pack of cards (no jokers).
 Play cards ONLY when they are
 1. 3 of a kind ('AAA' ) or 4 of a kind('AAAA').
 2. a straight flush of 3 or more cards('JQK' or 'A23456...' in the same suit).

 Find out whether the player will be able to play the whole hand given.

 */
public class PlayCards {

    public static void main(String[] args) {

        int[][] draw = new int[][]{{0,0},{0,12},{0,11},{0,10},{1,10},{2,10},{3,10}, {0, 9}, {1,0}, {1, 12}};

        int[][] cards = new int[4][13];

        int hand = 0;

        for(int[] card: draw) {
            cards[card[0]][card[1]] = 1; hand++;
        }

        System.out.println(handClear(cards, hand));
    }

    private static void printHand(int[][] cards) {

        for(int i = 0; i < 4; i++) {

            for(int j = 0; j < 13; j++) {

                if(cards[i][j] == 1)
                    System.out.print("("+i+", "+j+")  ");

            }

        }
        System.out.println();

    }

    //input a 4X13 matrix with 4 suits and 13 ranks of cards. set cards[suit][rank] to 1 if this card in hand.
    public static boolean handClear(int[][] cards, int hand) {

        if(hand == 0) return true;

        for(int rank = 12; rank >= 0; rank--) {

            for(int suit = 0; suit < 4; suit++) {

                if(cards[suit][rank] == 1) { //if cards[suit][rank] in hand

                    cards[suit][rank] = 0; hand--;

                    int smallerRank = rank == 0 ? 12: rank - 1; // look for straight flush that end with this card
                                                                // watch for Ace as a special case that ***QKA and A23*** both valid
                    if(cards[suit][smallerRank] == 1) {

                        cards[suit][smallerRank] = 0; hand--;

                        int r = smallerRank - 1;

                        for(; r >= 0 && cards[suit][r] == 1; r--) {   //try playing the straight flush found

                            cards[suit][r] = 0; hand--;

                            if(handClear(cards, hand)) return true;

                        }

                        r++;

                        for(; r <= smallerRank; r++) {  //backtrack if play did not work

                            cards[suit][r] = 1; hand++;

                        }

                    }
                    //look for 3/4 of a kind for cards[suit][rand]
                    int n = cards[0][rank] + cards[1][rank] + cards[2][rank] + cards[3][rank];

                    if(n == 3 || n == 2) {

                        int tmp1 = cards[(suit + 1) % 4][rank],
                            tmp2 = cards[(suit + 2) % 4][rank],
                            tmp3 = cards[(suit + 3) % 4][rank];

                        cards[(suit + 1) % 4][rank] = 0; //try playing the 3/4 of a kind
                        cards[(suit + 2) % 4][rank] = 0;
                        cards[(suit + 3) % 4][rank] = 0;
                        hand -= n;

                        if(handClear(cards, hand)) return true;

                        cards[(suit + 1) % 4][rank] = tmp1;   //backtrack if play did not work
                        cards[(suit + 2) % 4][rank] = tmp2;
                        cards[(suit + 3) % 4][rank] = tmp3;
                        hand += n;

                    }

                    cards[suit][rank] = 1; hand++;
                }
            }
        }
        return false;
    }
}
