import java.util.*;
import java.io.*;
 
class Main
{
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];
        Queue<int[]> q = new ArrayDeque<>();
        int notRipedTomato = 0;
        for(int i = 0; i < row; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) q.offer(new int[]{i, j, 0});
                else if(map[i][j] == 0) notRipedTomato++;
            }
        }
        if(notRipedTomato == 0){
            System.out.println(0);
        }else{
            int count = 0;
            int time = 0;
            while(q.size() > 0){
                int[] now = q.poll();
                time = now[2];
                for(int i = 0; i < 4; i++){
                    int nR = now[0] + d[i][0];
                    int nC = now[1] + d[i][1];
                    if(nR < 0 || nC < 0 || nR >= row || nC >= col || map[nR][nC] == 1 || map[nR][nC] == -1) continue;
                    map[nR][nC] = 1;
                    q.offer(new int[]{nR, nC, now[2] + 1});
                    count++;
                } 
            }
            if(count == notRipedTomato) System.out.println(time);
            else System.out.println(-1);
        }
    }
    
}

