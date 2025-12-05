import java.io.*;
import java.util.*;
public class Main {
    static int[][] dp;
    static int[][] map;
    static int N;
    static int[][] d = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    static int dfs(int row, int col){
        if(dp[row][col] != 0) return dp[row][col];

        dp[row][col] = 1;

        for(int i = 0; i < 4; i++){
            int nR = row + d[i][0];
            int nC = col + d[i][1];

            if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
            if(map[nR][nC] > map[row][col]){
                dp[row][col] = Math.max(dp[row][col], dfs(nR, nC) + 1);
            }
        }

        return dp[row][col];
    }
}
