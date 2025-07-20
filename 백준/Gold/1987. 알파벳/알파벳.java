import java.io.*;
import java.util.*;
public class Main{
    static int max;
    static char[][] map;
    static int[][] visited;
    static final int[] dCol = {1, 0, -1, 0};
    static final int[] dRow = {0, 1, 0, -1};
    static int r;
    static int c;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new int[r][c];
        max = 1;
        for(int i = 0; i < r; i++){
            String s= br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = s.charAt(j);
            }
        }
        visited[0][0] = 1 << map[0][0] -'A';
        dfs(0, 0, 1, 1 << map[0][0] -'A');
        System.out.print(max);
    }
    static void dfs(int col, int row, int count, int visit){
        if(count == 26){
            max = 26;
            return;
        }
        visited[col][row] = visit;
        boolean check = false;
        for(int i = 0; i < 4; i++){
            int nextCol = col + dCol[i];
            int nextRow = row + dRow[i];
            if(nextCol < 0 || nextCol == r || nextRow < 0 || nextRow == c) continue;
            if((visit & (1 << map[nextCol][nextRow] -'A')) != 0) continue;
            if ((visit | 1 << map[nextCol][nextRow] - 'A') == visited[nextCol][nextRow]) {
                continue;
            }
            visited[nextCol][nextRow] = visit | (1 << map[nextCol][nextRow] -'A');
            check = true;
            dfs(nextCol, nextRow, count+1, visit | (1 << map[nextCol][nextRow] -'A') );
        }
        if(!check){
            max = Math.max(count,max);
        }


    }
}