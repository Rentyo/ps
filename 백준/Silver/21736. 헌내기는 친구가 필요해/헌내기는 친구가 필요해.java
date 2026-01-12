import java.io.*;
import java.util.*;
public class Main {
    static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    static boolean[][] v;
    static char[][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=  Integer.parseInt(st.nextToken());
        int M= Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int sRow = 0;
        int sCol = 0;
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'I'){
                    sRow = i;
                    sCol = j;
                }
            }
        }

        v = new boolean[N][M];
        result = 0;
        v[sRow][sCol] = true;
        dfs(sRow, sCol);
        System.out.println(result == 0 ? "TT" : result);
    }
    static void dfs(int row, int col){
        if(map[row][col] == 'P') result++;

        for(int i = 0; i < 4; i++){
            int nextRow = row + d[i][0];
            int nextCol = col + d[i][1];

            if(nextRow < 0 || nextCol < 0 || nextRow >= map.length || nextCol >= map[0].length || map[nextRow][nextCol] == 'X' || v[nextRow][nextCol]) continue;
            v[nextRow][nextCol] = true;
            dfs(nextRow, nextCol);
        }
    }


}
