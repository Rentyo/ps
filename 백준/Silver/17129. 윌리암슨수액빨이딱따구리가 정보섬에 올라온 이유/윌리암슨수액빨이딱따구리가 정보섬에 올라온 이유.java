import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j) - '0';
                if(map[i][j] == 2) {
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
            }
        }

        int result = -1;
        boolean find = false;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(find) break;

            for(int i = 0; i < 4; i++){
                int nR = cur[0] + d[i][0];
                int nC = cur[1] + d[i][1];

                if(nR < 0 || nR >= N || nC < 0 || nC >= M || visited[nR][nC] || map[nR][nC] == 1) continue;
                if(map[nR][nC]== 3 || map[nR][nC]==4 || map[nR][nC]== 5 ){
                    result = cur[2] + 1;
                    find = true;
                    break;
                }
                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC, cur[2] +1});
            }
        }
        StringBuilder sb = new StringBuilder();
        if(result == -1){
            sb.append("NIE");
        }else{
            sb.append("TAK").append("\n").append(result);
        }
        System.out.println(sb);

    }
}
