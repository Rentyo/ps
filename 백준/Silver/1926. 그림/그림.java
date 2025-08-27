import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException  {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] v = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m; j++){
                v[i][j] = Integer.parseInt(st.nextToken()) == 0;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        int total = 0;
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(v[i][j]) continue;
                total ++;
                v[i][j] = true;
                int area = 1;
                q.offer(new int[]{i, j});
                while(!q.isEmpty()){
                    int[] cur = q.poll();

                    for(int k = 0; k < 4; k++){
                        int nRow = cur[0] + d[k][0];
                        int nCol = cur[1] + d[k][1];
                        if(nRow < 0 || nRow >= n || nCol < 0 || nCol >= m || v[nRow][nCol]) continue;
                        q.offer(new int[]{nRow, nCol});
                        v[nRow][nCol] = true;
                        area++;
                    }
                }
                max = Math.max(max, area);
            }
        }
        sb.append(total).append("\n");
        sb.append(max).append("\n");
        System.out.println(sb);
    }
}
