import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] v;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new int[100_001];
        Arrays.fill(v, 100_002);

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{N, 0});
        v[N] = 0;
        while(dq.size() > 0){
            int[] cur =  dq.pollFirst();

            if(cur[0] == M){
                System.out.println(cur[1]);
                return;
            }
            int next1 = cur[0] * 2;
            int next2 = cur[0] + 1;
            int next3 = cur[0] - 1;

            if(next1 <= 100000 && v[next1] > cur[1]){
                v[next1] = cur[1];
                dq.offerFirst(new int[]{next1, cur[1]});
            }

            if(next2 <= 100000 && v[next2] > cur[1] + 1){
                v[next2] = cur[1] + 1;
                dq.offerLast(new int[]{next2, cur[1] + 1});
            }
            if(next3 >= 0 && v[next3] > cur[1] + 1){
                v[next3] = cur[1] + 1;
                dq.offerLast(new int[]{next3, cur[1] + 1});
            }

        }
    }

}
