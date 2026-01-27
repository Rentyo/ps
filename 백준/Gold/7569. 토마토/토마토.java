import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
        {1, 0, 0},
        {0, 1, 0},
        {0, 0, 1},
        {-1, 0, 0},
        {0, -1, 0},
        {0, 0, -1}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] map = new int[H][N][M];

        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1 ) queue.offer(new int[]{i, j, k, 0});
                }
            }
        }   
        int day = 0;
        while(queue.size() > 0){
            int[] cur = queue.poll();
            day = cur[3];
            for(int i = 0; i < 6; i++){
                int nextH = cur[0] + d[i][0];
                int nextN = cur[1] + d[i][1];
                int nextM = cur[2] + d[i][2];

                if(nextH < 0 || nextN < 0 || nextM < 0 || nextH >= H || nextN >= N || nextM >= M || map[nextH][nextN][nextM] != 0) continue;
                map[nextH][nextN][nextM] = 1;
                queue.offer(new int[]{nextH, nextN, nextM, cur[3] + 1});
            }
        }
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < M; k++){
                    if(map[i][j][k] == 0 ){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }  
        System.out.println(day);
    }
}