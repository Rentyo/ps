import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[3][N+1];

        // 0 : 마지막이 1 x 2일 때
        // 1 : 마지막이 2 x 1일 때
        // 2 : 마지막이 2 x 2일 때
        if(N == 1) {
            System.out.println(1);
            return;
        }else if(N == 2){
            System.out.println(3);
            return;
        }
        dp[1][1] = 1;
        dp[0][2] = 1;
        dp[1][2] = 1;
        dp[2][2] = 1;

        for(int i = 3; i <= N; i++){
            dp[0][i] = ((dp[0][i-2] + dp[1][i-2]) % 10007 + dp[2][i-2]) % 10007;
            dp[2][i] = ((dp[0][i-2] + dp[1][i-2]) % 10007 + dp[2][i-2]) % 10007;
            dp[1][i] = ((dp[0][i-1] + dp[1][i-1]) % 10007 + dp[2][i-1]) % 10007;
        }

        System.out.println( ((dp[0][N]+ dp[1][N])%10007 + dp[2][N]) % 10007);

    }
}
