import java.io.*;
import java.util.*;
public class Main { 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] H = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[M];
        int[] right = new int[M];

        // 왼쪽 최대
        left[0] = H[0];
        for (int i = 1; i < M; i++) {
            left[i] = Math.max(left[i-1], H[i]);
        }

        // 오른쪽 최대
        right[M-1] = H[M-1];
        for (int i = M-2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], H[i]);
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            answer += Math.min(left[i], right[i]) - H[i];
        }

        System.out.println(answer);
    }
}
