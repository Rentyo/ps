import java.io.*;
import java.util.*;
public class Solution {
    static final int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= t; test_case++){
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            int[][] arr= new int[n][n];
            boolean[][] v = new boolean[n][n];
            for(int i = 0 ; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            st= new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            st= new StringTokenizer(br.readLine());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return o1[2] - o2[2];
                }
            });
            v[startRow][startCol] = true;
            q.offer(new int[]{startRow, startCol, 0});
            int result = -1;
            while( q.size() > 0 ){
                int[] now = q.poll();

                if(now[0] == endRow && now[1] == endCol){
                    result = now[2];
                    break;
                }
                for(int i = 0; i < 4; i++){
                    int nextRow = now[0] + d[i][0];
                    int nextCol = now[1] + d[i][1];
                    if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                    if(v[nextRow][nextCol]) continue;
                    if(arr[nextRow][nextCol] == 2){
                        int plus = 0;
                        if((now[2] + 1)%3 == 1) plus += 2;
                        else if((now[2] +1)%3 == 2) plus += 1;
                        q.offer(new int[]{nextRow, nextCol, (now[2]+1) + plus});
                    }else if(arr[nextRow][nextCol]==0){
                        q.offer(new int[]{nextRow, nextCol, now[2] + 1});
                    }
                    v[nextRow][nextCol] = true;
                }
            }
            sb.append(result).append("\n");
            
        }
        System.out.print(sb);
    }
    
}
