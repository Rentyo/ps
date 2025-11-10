import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] track = new int[N+1];
        Arrays.fill(dp, 1_000_001);
        dp[N] = 0;

        for(int i = N; i >= 1 ; i--){
            if (i - 1 >= 1 && dp[i] + 1 < dp[i - 1]) {
                dp[i - 1] = dp[i] + 1;
                track[i - 1] = i;
            }
            if(i % 3 == 0 && dp[i] + 1 < dp[i/3]){
                dp[i / 3] = dp[i] + 1;
                track[i/3] = i;
            }
            if(i % 2 == 0 && dp[i] + 1 < dp[i/2]){
                dp[i / 2] = dp[i] +1;
                track[i/2] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dp[1]).append("\n");
        int index = 1;
        List<Integer> list = new ArrayList<>();
        while (index != 0) {
            list.add(index);
            index = track[index];
        }
        Collections.reverse(list);
        for (int x : list) {
            sb.append(x).append(" ");
        }
        System.out.println(sb);
    }
}
