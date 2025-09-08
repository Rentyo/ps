import java.util.*;
import java.io.*;
class Main
{   
    static int[] volumes;
    static int[] values;
    static int[][] dp;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
        // 0 : 현재 행이 가로 일 때, 1 : 현재 행이 세로 일 때 
        int[][] dp = new int[2][n];
        dp[0][0] = 0;
        dp[1][0] = 1;
        if(n >=2){
            dp[0][1] = 1;
            dp[1][1] = 1;
        }
        
        for(int i = 2; i < n; i++){
            dp[0][i] = (dp[0][i-2] %10_007 + dp[1][i-2] %10_007) %10_007;
            dp[1][i] = (dp[0][i-1] %10_007 + dp[1][i-1] %10_007) %10_007;
        }

        System.out.println( (dp[0][n-1] %10_007 + dp[1][n-1] %10_007 )%10_007) ;


	}
}