import java.io.*;
public class Main {
    static long[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int t_c = Integer.parseInt(br.readLine());
        dp = new long[81][10];
        for(int i = 0; i <= 9; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i <= 80; i++){
            for(int j = 1; j <= 9; j++){
                for(int k = j; k <= 9; k++){
                    dp[i][j] += dp[i-1][k];
                }
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < t_c; t++){
            String s = br.readLine();
            if(check(s)){
                sb.append(calculate(s)).append("\n");
            }else{
                sb.append(-1).append("\n");
            }

        }
        System.out.println(sb);
    }
    public static boolean check(String s){
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) < s.charAt(i-1)) return false;
        }
        return true;
    }
    public static long calculate(String s){
        long result = 0;

        int n = s.length();

        for(int len = 1; len < n; len++){
            for(int start = 0; start <= 9; start++){
                result += dp[len][start];
            }
        }

        int prev = 0; 
        for(int i = 0; i < n; i++){
            int cur = s.charAt(i) - '0';

            for(int digit = prev; digit < cur; digit++){
                result += dp[n - i][digit];
            }

            prev = cur; 
        }

        return result;
    }   
}
