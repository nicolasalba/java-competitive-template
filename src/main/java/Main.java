import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());

        final Solver solver = new Solver();
        bw.write(String.valueOf(solver.solve(n)));
        bw.flush();
    }
}

class Solver {
     public long solve(long n) {
         return n;
     }
}
