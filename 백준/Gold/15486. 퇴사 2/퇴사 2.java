
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 0 은 상담 날짜 1은 value
        int[][] counsels = new int[2][n];
        StringTokenizer st;
        int[] dp = new int[n+1];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            counsels[0][i] = Integer.parseInt(st.nextToken());
            counsels[1][i] = Integer.parseInt(st.nextToken());
        }
        for(int i = n-1; i >= 0; i--){
            dp[i] = dp[i+1];
            if(i + counsels[0][i] <= n) dp[i] = Math.max(dp[i+counsels[0][i]]+counsels[1][i]  , dp[i]);
        }
        System.out.println(dp[0]);

    }
}
