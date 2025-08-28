import java.io.*;
import java.util.*;
public class Main {
    static int[][] d ={
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            boolean[][] visited = new boolean[n][n];
            Deque<int[]> deque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());
            deque.offer(new int[]{startRow, startCol, 0});
            visited[startRow][startCol] = true;
            int result = -1;
            while(!deque.isEmpty()){
                int[] curr = deque.poll();
                if(curr[0] == endRow && curr[1] == endCol){
                    result = curr[2];
                    break;
                }

                for(int j = 0; j < d.length; j++){
                    int nRow = curr[0] + d[j][0];
                    int nCol = curr[1] + d[j][1];
                    if(nRow < 0 || nCol < 0 || nRow >= n || nCol >= n || visited[nRow][nCol]) continue;
                    deque.offer(new int[]{nRow, nCol, curr[2] + 1});
                    visited[nRow][nCol] = true;
                }
            }
            System.out.println(result);
        }
    }

}
