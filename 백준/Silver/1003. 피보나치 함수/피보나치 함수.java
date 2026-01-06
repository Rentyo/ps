import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int[] dp_0 = new int[41];
        int[] dp_1 = new int[41];

        dp_0[0] = 1;
        dp_1[1] = 1;

        for(int i = 2; i <= 40; i++){
            dp_1[i] = dp_1[i-1] + dp_1[i-2];
            dp_0[i] = dp_0[i-1] + dp_0[i-2];
        }

        for(int t_c = 1; t_c <= T; t_c++){
            int now = Integer.parseInt(br.readLine());
            sb.append(dp_0[now]).append(" ").append(dp_1[now]).append("\n");
        }
        System.out.print(sb);
    }
}

