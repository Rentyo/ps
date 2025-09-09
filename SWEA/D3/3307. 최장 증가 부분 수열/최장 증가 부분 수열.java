import java.util.*;
import java.io.*;
class Solution
{   
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t_c = 1; t_c <= t; t_c++){
            sb.append("#").append(t_c).append(" ");
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[] dp = new int[n];
            int max = 1;
            for(int i = 0; i < n; i++){
                dp[i] = 1;
                for(int j = 0; j < i; j++){
                    if(arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
}