package Google;

/**
 * find number of polygons in a matrix of '/' and '\'
 */
public class NumberOfPolygons {

    char[][] matrix; //has only slashes and backslashes


    public void print() {
        for(char[] a: matrix) {
            for(char c: a) System.out.print(c);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //test case 1
        NumberOfPolygons n = new NumberOfPolygons(new char[][]{{'\\', '/'}, {'/', '/'}});
        n.print();
        System.out.println(n.numberOfPolygons());

        //test case 2
        n = new NumberOfPolygons(new char[][]{{'\\', '\\'}, {'\\', '\\'}, {'\\', '/'}});
        n.print();
        System.out.println(n.numberOfPolygons());

        //test case 3
        n = new NumberOfPolygons(new char[][]{{'\\', '\\', '/','\\','/'}
                                            , {'/', '/', '\\', '/', '\\'}
                                            , {'/', '/', '\\', '/', '\\'}});
        n.print();
        System.out.println(n.numberOfPolygons());
    }

    public NumberOfPolygons(char[][] m) {
        this.matrix = m;
    }

    //solution from here down
    public int numberOfPolygons() {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        boolean[][][] visited = new boolean[matrix.length][matrix[0].length][2]; //matrix[x][y][0] denotes upper half, matrix[x][y][0] denotes lower half
        int polygons = 0;
        for(int x = 0; x < matrix.length; x++) {
            for(int y = 0; y < matrix[0].length; y++) {
                for(int half = 0; half < 2; half++)
                    if(!visited[x][y][half]) {
                        dfs(x, y, half, visited);
                        polygons++;
                    }
            }
        }
        return polygons;
    }

    private void dfs(int x, int y, int whichhalf, boolean[][][] visited) {
        if(x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || visited[x][y][whichhalf]) {
            return;
        }
        int[][] children = getChildren(x, y, whichhalf); //every node has two children: children[0][x][y][whichhalf] and children[1][x][y][whichhalf]
        visited[x][y][whichhalf] = true;
        dfs(children[0][0], children[0][1],children[0][2], visited);
        dfs(children[1][0], children[1][1],children[1][2], visited);
    }

    //find the two children for current node (x,y,whichhalf)
    private int[][] getChildren(int x, int y, int whichhalf) {
        int[] child1 = new int[] {x, whichhalf == 0 ? y - 1: y + 1, 1 - whichhalf};
        int[] child2;
        if((matrix[x][y] == '\\' && whichhalf == 1) || (matrix[x][y] == '/' && whichhalf == 0)) {
            child2 = new int[] {x - 1, y, x > 0 && matrix[x - 1][y] == '/' ? 1: 0};
        } else {
            child2 = new int[] {x + 1, y, x + 1 < matrix.length && matrix[x + 1][y] == '/' ? 0: 1};
        }
        return new int[][] {child1, child2};
    }
}
