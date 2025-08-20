import java.io.*;
import java.util.*;
public class Main {
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    static int[][] arr;
    static boolean[][][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        v = new boolean[n][m][2];

        for(int i = 0; i < n; i++){
            String s= br.readLine();
            for(int j = 0; j < m; j++){
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.print(bfs());
    }

    public static int bfs(){

        int result = -1;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0, 1, 0});
        v[0][0][0] = true;

        while(deque.size() > 0){
            int[] now = deque.poll();

            if(now[0] == arr.length-1 && now[1] == arr[0].length-1){
                result = now[2];
                break;
            }

            for(int i = 0; i < 4; i++){
                int nextRow = now[0] + d[i][0];
                int nextCol = now[1] + d[i][1];
                if(nextRow <0 || nextCol <0 || nextRow >= arr.length || nextCol >= arr[0].length) continue;
                if(arr[nextRow][nextCol] == 1){
                    if(now[3] == 1 || v[nextRow][nextCol][now[3]]) continue;
                    v[nextRow][nextCol][now[3]] = true;
                    deque.offer(new int[]{nextRow, nextCol, now[2]+1, now[3]+1});
                }else{
                    if(v[nextRow][nextCol][now[3]]) continue;
                    v[nextRow][nextCol][now[3]] = true;
                    deque.offer(new int[]{nextRow, nextCol, now[2]+1, now[3]}); 
                }
                
            }
        }
        return result;
    }
    
}
