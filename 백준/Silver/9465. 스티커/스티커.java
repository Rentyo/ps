import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N + 1];
            int[][] dp = new int[2][N + 1];

            // 데이터 입력 (인덱스를 1부터 시작하면 계산이 훨씬 편합니다)
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 기저 상태 초기화
            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];

            // DP 수행
            for (int j = 2; j <= N; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + arr[1][j];
            }

            sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
        }
        System.out.print(sb);
    }
}
