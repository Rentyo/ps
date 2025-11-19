import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T= Integer.parseInt(br.readLine());
        int[] dp = new int[100];
        for(int i = 1; i <=99; i++){
            dp[i] = dp[i-1];
            if(i >= 10) dp[i] = Math.min(dp[i], dp[i-10]);
            if(i >= 25) dp[i] = Math.min(dp[i] , dp[i-25]);
            dp[i]++;
        }


        for(int t_c = 1; t_c <= T; t_c++){
            long k = Long.parseLong(br.readLine());
            int count = 0;
            while(k > 0){
                count += dp[(int)(k % 100)];
                k /= 100;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

}
