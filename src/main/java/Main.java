import java.io.*;
import java.util.StringTokenizer;

public class Main {
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
        bw.write(String.valueOf(solver.solve(n, k, board)));
        bw.flush();
    }
}

class Solver {
    public long solve(int n, int k, int[][] board) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int square = board[i][j];
                if (square == 1) {
                    int currentDistance = getPathDistance(n, k, board, 1, i, j, 0);
                    if (currentDistance < result) {
                        System.out.println(j + " " + i + " tiene " + currentDistance);
                        result = currentDistance;
                    }
                }
            }
        }
        return result;
    }

    public int getPathDistance(int n, int k, int[][] board, int currentK, int ai, int aj, int previousResult) {
        if (previousResult == -1) {
            return -1;
        }
        if (currentK == k) {
            return 0;
        }
        int theBest = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int square = board[i][j];
                if (square == currentK + 1) {
                    int squareDistance = getDistance(aj, j, ai, i);
                    int totalDistance =  squareDistance + getPathDistance(n, k, board, currentK + 1, i, j, previousResult);
                    if (totalDistance < theBest) {
                        theBest = totalDistance;
                    }
                }
            }
        }
        //System.out.println("Tn k " + currentK + " distance " + theBest);
        return theBest == Integer.MAX_VALUE ? -1 : theBest + previousResult;
    }

    private int getDistance(int x1, int x2, int y1, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

}
