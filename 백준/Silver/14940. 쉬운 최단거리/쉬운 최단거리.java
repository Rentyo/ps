import java.io.*;
import java.util.*;
public class Main {
    static int[][] s = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new  int[N][M];
        int[][] d = new int[N][M];
        int sRow =  -1, sCol = -1;
        for(int i = 0; i < N; i++){
            Arrays.fill(d[i] , 123456789);
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    sRow = i;
                    sCol = j;
                }else if(map[i][j] == 0){
                    d[i][j] = 0;
                }
            }
        }
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sRow, sCol, 0});
        d[sRow][sCol] = 0;
        while(!q.isEmpty() ){
            int[] cur = q.poll();
            for(int j = 0; j < 4; j++){
                int nRow = cur[0] + s[j][0];
                int nCol = cur[1] + s[j][1];
                if(nRow >= 0 && nRow < N && nCol >= 0 && nCol < M && map[nRow][nCol] == 1 && d[nRow][nCol] > cur[2] + 1 ){
                    d[nRow][nCol] = cur[2]+1;
                    q.offer(new int[]{nRow, nCol, cur[2]+1});
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(d[i][j] == 123456789 ? -1 : d[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
