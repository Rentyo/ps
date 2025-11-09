import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {
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

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken())-1;
        int startY = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        int endX = Integer.parseInt(st.nextToken())-1;
        int endY = Integer.parseInt(st.nextToken())-1;

        int[][][] v = new int[2][N][M];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < N; j++){
                Arrays.fill(v[i][j], 1_000_001);
            }
        }
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{startX, startY, 0, 0});
        v[0][startX][startY] = 0;
        int result = -1;

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int nowX = cur[0];
            int nowY = cur[1];
            int time = cur[2];
            int magic =  cur[3];
            if(nowX == endX && nowY == endY){
                result = time;
                break;
            }
            for(int i = 0; i < 4; i++){
                int nX = nowX + d[i][0];
                int nY = nowY + d[i][1];
                if(nX < 0 || nX >= N || nY < 0 || nY >= M) continue;

                if(magic == 0){
                    if(map[nX][nY] == 1){
                        if(v[1][nX][nY] > time + 1) {
                            v[1][nX][nY] = time + 1;
                            deque.offer(new int[]{nX, nY, time + 1, 1});
                        }
                    }else{
                        if(v[0][nX][nY] > time + 1) {
                            v[0][nX][nY] = time + 1;
                            deque.offer(new int[]{nX, nY, time + 1, 0});
                        }
                    }
                }else{
                    if(map[nX][nY] == 0){
                        if(v[1][nX][nY] > time + 1) {
                            v[1][nX][nY] = time + 1;
                            deque.offer(new int[]{nX, nY, time+1, 1});
                        }
                    }
                }
            }
        }
        System.out.println(result);

    }

}
