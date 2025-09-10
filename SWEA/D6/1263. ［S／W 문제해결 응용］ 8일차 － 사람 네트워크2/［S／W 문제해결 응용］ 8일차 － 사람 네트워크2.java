import java.util.*;
import java.io.*;
 
class Solution
{
    static final int maxValue = 1_000_000_000;
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
                    dp[i][j] = Integer.parseInt(st.nextToken());
                    if(dp[i][j] == 0 && i != j) dp[i][j] = maxValue;
                }
            }
            for(int mid = 0; mid < n; mid++){
                for(int start = 0; start < n; start++){
                    for(int end = 0; end < n; end++){
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid][end]);
                    }
                }
            }
            int min = maxValue;
            for(int i = 0; i < n; i++){
                int sum = 0;
                for(int j = 0 ; j < n; j++){
                    sum += dp[i][j];
                    if(sum > min) break;
                }
                min = Math.min(min, sum);
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }
}
