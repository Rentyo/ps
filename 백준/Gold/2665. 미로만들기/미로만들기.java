import java.io.*;
import java.util.*;
public class Main {
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        int[][] v = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            Arrays.fill(v[i], 2501);
            for(int j = 0; j < N; j++){
                arr[i][j] = s.charAt(j)-'0';
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[2], o2[2]);
            }
        });

        pq.offer(new int[]{0, 0, 0});

        while(pq.size() > 0){
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            int broken = cur[2]; 
            if(v[row][col] <= broken) continue;
            if(row == N-1 && col == N-1){
                System.out.println(broken);
                return;
            }
            v[row][col] = broken;

            for(int i = 0; i < 4; i++){
                int nR = row + d[i][0];
                int nC = col + d[i][1];
                if(nR < 0 || nC < 0 || nR >= N || nC >= N) continue;
                int nB = arr[nR][nC] == 0 ? broken + 1 : broken;

                if(v[nR][nC] > nB){
                    pq.offer(new int[]{nR, nC, nB});
                }
            }

        }


        
        
    }
}
