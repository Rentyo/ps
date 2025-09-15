import java.io.*;
import java.util.*;
public class Main {
    static ArrayList<Integer>[] map;
    static boolean[] v;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dp = new int[n][2];
        map = new ArrayList[n];
        v = new boolean[n];
        for(int i = 0; i < n; i++){
            map[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            map[start].add(end);
            map[end].add(start);
        }

        dfs(0);
        System.out.println(Math.min(dp[0][0], dp[0][1]));
    }
    static void dfs(int index){
        v[index] = true;
        dp[index][0] = 0; // 해당 전조등이 꺼졌을 때
        dp[index][1] = 1;
        for(int i = 0; i < map[index].size(); i++){
            if(v[map[index].get(i)])continue;
            dfs(map[index].get(i));
            dp[index][0] += dp[map[index].get(i)][1];
            dp[index][1] += Math.min(dp[map[index].get(i)][0], dp[map[index].get(i)][1]);
        }
    }
}
