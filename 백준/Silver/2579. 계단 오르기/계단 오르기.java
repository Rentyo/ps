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
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[n];
        dp[0] = arr[0];
        if(n >= 2) dp[1] = arr[0] + arr[1];
        if(n >= 3) dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);

        for(int i = 3; i < n; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i];
        }
        System.out.println(dp[n-1]);
        

	}
}