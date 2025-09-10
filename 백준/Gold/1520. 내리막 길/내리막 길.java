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
    static boolean[][] v;
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
            }
        }
        v = new boolean[n][m];
        dfs(0, 0);
        System.out.println(dp[0][0]);
    }
    public static int dfs(int row, int col){

        if(row == n-1 && col == m-1){
            v[row][col] = true;
            return 1;
        }

        int sum = 0;
        for(int i = 0; i < 4; i++){
            int nR = row + d[i][0];
            int nC = col + d[i][1];
            if(nR < 0 || nC < 0 || nR >= n || nC >= m || map[row][col] <= map[nR][nC]) continue;
            if(v[nR][nC]) sum += dp[nR][nC];
            else{
                sum += dp[nR][nC] = dfs(nR, nC);
            }
        }
        v[row][col] = true;
        return dp[row][col] = sum;
    }
}