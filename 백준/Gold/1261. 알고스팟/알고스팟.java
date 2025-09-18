import java.io.*;
import java.util.*;
public class Main {
    static int[][] d ={
            {1,0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] map = new  char[N][M];
        int[][] cost = new int[N][M];
        for(int i=0;i<N;i++){
            String s = br.readLine();
            Arrays.fill(cost[i],Integer.MAX_VALUE);
            for(int j=0;j<M;j++){
                map[i][j] = s.charAt(j);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2],o2[2]);
            }
        });
        pq.offer(new int[]{0,0,0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[2] >= cost[cur[0]][cur[1]]) continue;
            cost[cur[0]][cur[1]] = cur[2];
            if(cur[0] == N-1 && cur[1] == M-1) break;
            for(int i = 0; i < 4; i++){
                int nR = cur[0] + d[i][0];
                int nC = cur[1] + d[i][1];
                if(nR < 0 || nR >= N || nC < 0 || nC >= M) continue;
                int nV = cur[2];
                if(map[nR][nC] == '1') nV++;
                if(nV >= cost[nR][nC]) continue;
                pq.offer(new int[]{nR,nC,nV});
            }
        }

        System.out.println(cost[N-1][M-1]);

    }
}
