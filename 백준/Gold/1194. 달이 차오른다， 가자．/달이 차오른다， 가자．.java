import java.io.*;
import java.util.*;
public class Main {
    static int[][] d = 
    {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    static class Point{
        int row;
        int col;
        int cost;
        int keys;
        public Point(int row, int col, int cost, int keys){
            this.row = row;
            this.col = col;
            this.cost = cost;
            this.keys = keys;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];

        int startRow = -1;
        int startCol = -1;
        Set<ArrayList<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == '0'){
                    startRow =i;
                    startCol = j;
                }
            }
        }

        boolean[][][] v = new boolean[n][m][1 << 6];
        v[startRow][startCol][0] = true;
        
        Deque<Point> deque = new ArrayDeque<>();
        deque.offer(new Point(startRow, startCol, 0, 0));

        int result = -1;
        while(deque.size() > 0){
            Point now = deque.poll();
            if(map[now.row][now.col] == '1'){
                result = now.cost;
                break;
            }

            for(int i = 0; i < 4; i++){
                int nextRow = now.row + d[i][0];
                int nextCol = now.col + d[i][1];
                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
                if(v[nextRow][nextCol][now.keys] || map[nextRow][nextCol] == '#') continue;
                

                if(map[nextRow][nextCol] == '.' || map[nextRow][nextCol] == '0' || map[nextRow][nextCol] == '1'){
                    v[nextRow][nextCol][now.keys] = true;
                    deque.offer(new Point(nextRow, nextCol, now.cost+1, now.keys));
                }else if(map[nextRow][nextCol] >= 'A' && map[nextRow][nextCol] <= 'Z'){
                    if( (now.keys & (1 << (map[nextRow][nextCol] - 'A') ))  > 0){
                        v[nextRow][nextCol][now.keys] = true;
                        deque.offer(new Point(nextRow, nextCol, now.cost+1, now.keys));
                    }
                }else{
                    v[nextRow][nextCol][now.keys | (1 << (map[nextRow][nextCol] - 'a')) ] = true;
                    deque.offer(new Point(nextRow, nextCol, now.cost+1, now.keys | (1 << (map[nextRow][nextCol] - 'a' ))));
                }
            }
        }
        System.out.print(result);
    }
    
}
