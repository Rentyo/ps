import java.util.*;
import java.io.*;

class Solution
{
    static final int maxValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] dp = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    int now = Integer.parseInt(st.nextToken());
                    if(i != j){
                        if(now == 1) dp[i][j] = 1;
                        else dp[i][j] = maxValue;
                    }
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dp[j][i] == maxValue) continue;
                    for(int k = 0; k < n; k++){
                        if(dp[i][k] == maxValue || j == k) continue;
                        dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                    }
                }
            }
            int min = maxValue;
            for(int i = 0; i < n; i++){
                boolean check = false;
                int sum = 0;
                for(int j = 0; j < n; j++){
                    if(dp[i][j] == maxValue){
                        check = true;
                        break;
                    }
                    sum += dp[i][j];
                }
                if(!check) min = Math.min(min, sum);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
}