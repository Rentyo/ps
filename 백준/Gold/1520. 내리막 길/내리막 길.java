import java.util.*;
import java.io.*;

class Main
{
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    static int n;
    static int m;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for(int i = 0; i < n ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
    public static int dfs(int row, int col){

        if(row == n-1 && col == m-1){
            return 1;
        }
        if(dp[row][col] != -1) return dp[row][col];

        int sum = 0;
        for(int i = 0; i < 4; i++){
            int nR = row + d[i][0];
            int nC = col + d[i][1];
            if(nR < 0 || nC < 0 || nR >= n || nC >= m || map[row][col] <= map[nR][nC]) continue;
            sum += dfs(nR, nC);
        }
        return dp[row][col] = sum;
    }
}