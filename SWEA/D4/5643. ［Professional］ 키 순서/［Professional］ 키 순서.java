import java.util.*;
import java.io.*;
 
class Solution
{   
    static int[][] dp;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            dp = new int[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0;j < n; j++){
                    if(i == j) dp[i][j] = 0;
                    else dp[i][j] = 501;
                }
            }
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                dp[start][end] = 1;
            }

            for(int mid = 0; mid < n; mid++){
                for(int start = 0; start < n; start++){
                    if(dp[start][mid] == 501 || start==mid) continue;
                    for(int end = 0; end < n; end++){
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid][end]);
                    }
                }
            }
            int ans = 0;
            for(int i = 0; i < n; i++){
                int count = 0;
                for(int j = 0; j < n; j++){
                    if(dp[i][j] != 501 || dp[j][i] != 501) count++;
                }
                if(count == n) ans++;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}

