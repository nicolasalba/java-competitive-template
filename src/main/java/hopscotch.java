import java.io.*;
import java.util.StringTokenizer;

public class hopscotch {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        final Solver solver = new Solver();
        bw.write(String.valueOf(solver.solve(n, k, board, 0, -1, -1, 0)));
        bw.flush();
    }
}

class Solver {
    public int solve(int n, int k, int[][] board, int currentK, int ai, int aj, int previousResult) {
        if (previousResult == -1) {
            return -1;
        }
        if (currentK == k) {
            return 0;
        }
        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == currentK + 1) {
                    int totalDistance =  getDistance(aj, j, ai, i) + solve(n, k, board, currentK + 1, i, j, previousResult);
                    if (totalDistance < minPath) {
                        minPath = totalDistance;
                    } else if (totalDistance == 0) {
                        return -1;
                    }
                }
            }
        }
        return minPath == Integer.MAX_VALUE ? -1 : minPath + previousResult;
    }

    private int getDistance(int x1, int x2, int y1, int y2) {
        if (x1 == -1) {
            return 0;
        }
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

}
