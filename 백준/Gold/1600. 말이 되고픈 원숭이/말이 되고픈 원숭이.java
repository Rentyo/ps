
import java.io.*;
import java.util.*;

public class Main {
    public static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {2, 1},
            {1, 2},
            {-1, 2},
            {-2, 1},
            {-2, -1},
            {-1, -2},
            {1, -2},
            {2, -1}
    };
    static int[][] map;
    static boolean[][][] v;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[n][m][k+1];
        System.out.println(bfs());
    }
    public static int bfs(){
        Deque<Point> q = new ArrayDeque<>();
        q.offer(new Point(0, 0, 0, 0));
        v[0][0][0] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            if(p.row == map.length-1 && p.col == map[0].length-1){
                return p.cost;
            }
            for(int i = 0; i < (p.horseCount >= k ? 4 : d.length); i++){
                int nextRow = p.row + d[i][0];
                int nextCol = p.col + d[i][1];
                if(nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) continue;
                if(map[nextRow][nextCol] == 0 && !v[nextRow][nextCol][i >= 4 ? p.horseCount+1 : p.horseCount]){
                    v[nextRow][nextCol][i >= 4 ? p.horseCount+1 : p.horseCount] = true;
                    q.offer(new Point(nextRow, nextCol, p.cost+1, i >= 4 ? p.horseCount+1 : p.horseCount));
                }

            }
        }
        return -1;
    }
    static class Point{
        int row;
        int col;
        int cost;
        int horseCount;
        public Point(int row, int col, int cost, int horseCount) {
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.horseCount = horseCount;
        }
    }
}
