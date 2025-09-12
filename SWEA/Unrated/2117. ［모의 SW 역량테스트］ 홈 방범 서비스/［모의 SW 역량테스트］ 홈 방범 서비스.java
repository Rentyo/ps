import java.util.*;
import java.io.*;
 
class Solution
{   
    static int[][] d = {
        // 오른 아래
        {1, 1},
        // 왼 아래
        {1, -1},
        // 왼 위
        {-1, -1},
        // 오른 위
        {-1, 1}
    };
    static int[][][] dp;
    static int max;
    // 격자판의 크기
    static int n;
    // 집 마다의 비용
    static int m;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            dp = new int[n+2][n][n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    dp[0][i][j] =Integer.parseInt(st.nextToken());
                }
            }
            max = 1;
            int value = 1;
            for(int i = 2; i <= n+1; i++){
                value += 4*(i-1);
                for(int j = 0; j < n; j++){
                    for(int k = 0; k < n; k++){
                        simulate(j, k , i);
                        if(dp[i-1][j][k]*m >= value){
                            max = Math.max(dp[i-1][j][k] , max);
                        } 
                    }
                }
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb);
    }
    static void simulate(int row, int col, int size){
        int startRow = row - (size-1);
        int startCol = col;

        int count = 0; 
        for(int i = 0; i < 4; i++){
            for(int j = 1; j < size; j++){
                startRow += d[i][0];
                startCol += d[i][1];
                if(startRow < 0 || startRow >= n || startCol < 0 || startCol >=n) continue;
                count += dp[0][startRow][startCol];
            }
        }

        dp[size-1][row][col] = dp[size-2][row][col] + count;

    }
}