import java.io.*;
import java.util.*;

public class hopscotch {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, List<Position>> board = new HashMap<>();
        for (int i = 1; i <= k; i++) {
            board.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                Position position = new Position(i, j);
                int value = Integer.parseInt(st.nextToken());
                board.get(value).add(position);
            }
        }

        final Solver solver = new Solver();
        Position startPosition = new Position(-1, -1);
        bw.write(String.valueOf(solver.solve(n, k, board, 0, startPosition, 0)));
        bw.flush();
    }
}

class Solver {
    public int solve(int n, int k, Map<Integer, List<Position>> board, int currentK, Position beforePosition, int previousResult) {
        if (previousResult == -1) {
            return -1;
        }
        if (currentK == k) {
            return 0;
        }
        int minPath = Integer.MAX_VALUE;
        for (Position position : board.get(currentK + 1)) {
            int totalDistance =  getDistance(beforePosition, position) + solve(n, k, board, currentK + 1, position, previousResult);
            if (totalDistance == 0) {
                return -1;
            } else if (totalDistance < minPath) {
                minPath = totalDistance;
            }
        }
        return minPath == Integer.MAX_VALUE ? -1 : minPath + previousResult;
    }

    private int getDistance(Position beforePosition, Position currentPosition) {
        if (beforePosition.x == -1) {
            return 0;
        }
        return Math.abs(beforePosition.x - currentPosition.x) + Math.abs(beforePosition.y - currentPosition.y);
    }

}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
