import java.io.*;
import java.util.*;
public class Main{
    public static final int[] dCol = {1, 0, -1, 0};
    public static final int[] dRow = {0, 1, 0, -1};
    public static char[][] map;
    public static boolean[] visited;
    public static int r;
    public static int c;
    public static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean['Z'-'A' + 1];
        for(int i = 0; i< r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = s.charAt(j);
            }
        }
        visited[map[0][0] -'A'] = true;
        dfs(0, 0, 1);
        System.out.print(max);
    }
    public static void dfs(int col, int row, int count){
        max = Math.max(max,count);
        for(int i  = 0; i < 4; i++){
            int nextCol = col + dCol[i];
            int nextRow = row + dRow[i];
            if(nextCol < 0 || nextCol == r || nextRow < 0 || nextRow == c) continue;
            else if(visited[map[nextCol][nextRow] - 'A']) continue;
            visited[map[nextCol][nextRow] - 'A'] = true;
            dfs(nextCol, nextRow, count+1);
            visited[map[nextCol][nextRow] - 'A'] = false;

        }

    }
}