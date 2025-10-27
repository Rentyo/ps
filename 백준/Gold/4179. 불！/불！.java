import java.io.*;
import java.util.*;

public class Main {
    public static final int[][] d = {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Queue<int[]> fires =  new ArrayDeque<>();
        Queue<int[]> player = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                char now =  s.charAt(j);
                switch(now){
                    case '#' : map[i][j] = 1; break;
                    case 'F' : map[i][j] = 1; fires.offer(new int[]{i, j, 1}); break;
                    case 'J' : {
                        if(i == 0 || i == N-1 || j ==0 || j == M-1){
                            System.out.println(1);
                            return;
                        }
                        player.offer(new int[]{i, j, 1});
                        map[i][j] = 0; break;
                    }
                    default: map[i][j] = 1000_001; break;
                }
            }
        }
        while(!fires.isEmpty() || !player.isEmpty()){
            int fireSize = fires.size();
            for(int j = 0; j < fireSize; j++){
                int[] cur = fires.poll();

                for(int i = 0; i < 4; i++){
                    int nR = cur[0] + d[i][0];
                    int nC = cur[1] + d[i][1];

                    if(nR < 0 || nR >= N || nC < 0 || nC >= M || cur[2] + 1 >= map[nR][nC]) continue;

                    map[nR][nC] = cur[2] + 1;
                    fires.offer(new int[]{nR, nC, cur[2] + 1});
                }
            }
            int playerSize = player.size();

            for(int j = 0; j < playerSize; j++){
                int[] cur = player.poll();

                for(int i = 0; i < 4; i++){
                    int nR = cur[0] + d[i][0];
                    int nC = cur[1] + d[i][1];

                    if(nR < 0 || nR >= N || nC < 0 || nC >= M || cur[2] + 1 >= map[nR][nC]) continue;

                    if(nR == 0 || nR == N-1 || nC == 0 || nC == M-1){
                        System.out.println(cur[2]+1);
                        return;
                    }
                    map[nR][nC] = cur[2] + 1;
                    player.offer(new int[]{nR, nC, cur[2] + 1});
                }
            }

            if(player.size() == 0){
                break;
            }
        }


        System.out.println("IMPOSSIBLE");
    }
}
