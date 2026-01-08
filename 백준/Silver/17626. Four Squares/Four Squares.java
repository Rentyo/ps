import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        Arrays.fill(dp, 5);
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= (int)Math.sqrt(i); j++){
                dp[i] = Math.min(dp[i], dp[i - (int)Math.pow(j, 2)] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}