import java.io.*;
import java.util.*;
public class Main {
    public static class Hotel {
        int cost;
        int customer;
        public Hotel(int cost, int customer){
            this.cost = cost;
            this.customer = customer;
        }
    }
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Hotel[] hotels = new Hotel[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            hotels[i] = new Hotel(cost, customer);
        }
        int[] dp = new int[C+100];
        Arrays.fill(dp, 100025);
        dp[0] = 0;



        for(int i = 1; i < C+100; i++){
            for(int j = 0; j < N; j++){
                dp[i] = Math.min(dp[i], dp[Math.max(i-hotels[j].customer, 0)] + hotels[j].cost);
            }
        }
        int min = 100025;
        for(int i = C; i< C+100; i++){
            min = Math.min(dp[i], min);
        }
        System.out.println(min);
        
    }
}
