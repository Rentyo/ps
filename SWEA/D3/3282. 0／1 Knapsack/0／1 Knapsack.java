import java.util.*;
import java.io.*;
class Solution
{   
    static int[] volumes;
    static int[] values;
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
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            dp = new int[2][K+1];
            volumes = new int[N];
            values = new int[N];
            for(int i = 0; i < N; i++){
                st= new StringTokenizer(br.readLine());
                volumes[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());
            }
            boolean pre = false;
            for(int now = 0; now < N; now++){
                for(int i = 0; i <= K; i++){
                    dp[pre ? 0 : 1][i] = dp[pre ? 1 : 0][i];
                    if(i >= volumes[now]) dp[pre ? 0 : 1][i] = Math.max(dp[pre ? 1 : 0][i-volumes[now]] + values[now] , dp[pre ? 0 : 1][i]);
                }
                pre = !pre;
            }
            sb.append(N % 2 == 0 ? dp[0][K] : dp[1][K]).append("\n");
		}
        System.out.print(sb);
	}
}