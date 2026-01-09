import java.io.*;
import java.util.*;
public class Main {
    static boolean[][] map;
    static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            StringTokenizer st;

            for(int tc = 1; tc <= T; tc++){
                st = new StringTokenizer(br.readLine());
                int M  = Integer.parseInt(st.nextToken());
                int N = Integer.parseInt(st.nextToken());
                int K = Integer.parseInt(st.nextToken());

                map = new boolean[N][M];

                for(int j = 0; j < K; j++){
                    st = new StringTokenizer(br.readLine());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    map[y][x] = true;
                }
                sb.append(bfs()).append("\n");
            }
            System.out.print(sb);
    }
    public static int bfs(){
        int count = 0;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(!map[i][j]) continue;
                count++;
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                map[i][j] = false;
                while(!q.isEmpty()){
                    int[] cur = q.poll();
                    for(int k = 0; k < 4; k++){
                        int nRow = cur[0] + d[k][0];
                        int nCol = cur[1] + d[k][1];
                        if(nRow < 0 || nCol < 0 || nRow >= map.length || nCol >= map[i].length || !map[nRow][nCol]) continue;
                        map[nRow][nCol] = false;
                        q.offer(new int[]{nRow, nCol});
                    }
                }
            }
        }
        return count;
    }
}