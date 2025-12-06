import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K+1];
        Arrays.fill(dp, 123456789);
        dp[A] = 0;

        for(int i = A+1; i <= K; i++){
            if(i % 2 == 0){
                dp[i] = Math.min(dp[i-1], dp[i/2]) + 1;
            }else{
                dp[i] = dp[i-1] + 1;
            }
        }
        System.out.println(dp[K]);
    }
}
