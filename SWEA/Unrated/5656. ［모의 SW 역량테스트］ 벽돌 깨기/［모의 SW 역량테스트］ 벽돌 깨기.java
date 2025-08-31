import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    static int n;
    static int[][][] map;
    static int total;
    static int max;
    static int h;
    static int w;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc= 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[n+1][h][w];
            max = 0;
            total = 0;
            for(int i = 0; i < h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++){
                    int x = Integer.parseInt(st.nextToken());
                    if(x == 0) continue;
                    map[0][i][j] = x;
                    total++;
                }
            }

            dfs(0, 0, 0);
            sb.append(total - max).append("\n");
        }
        System.out.print(sb);
    }
    static void dfs(int index, int count, int breakPoint){
        if(breakPoint == total){
            max = total;
            return;
        }
        if(count == n){
            max = Math.max(max, breakPoint);
            return;
        }
        if(index == w) return;
        int topPos = check(index, count);
        if(topPos != -1) dfs(0, count+1, breakPoint + Simulation(index, count, topPos));
        dfs(index+1, count, breakPoint);
    }
    static int Simulation(int index, int count, int topPos){
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                map[count+1][i][j] = map[count][i][j];
            }
        }
        Deque<int[]> deque = new ArrayDeque<>();
        int bP = 0;
        deque.offer(new int[]{topPos, index});
        while(deque.size() > 0){
            int[] cur = deque.poll();
            int pow = map[count+1][cur[0]][cur[1]];
            if(map[count+1][cur[0]][cur[1]] == 0) continue;
            map[count+1][cur[0]][cur[1]] = 0;
            bP++;

            for(int i = 0; i < 4; i++){
                for(int j = 1; j < pow; j++){
                    int nR = cur[0] + d[i][0] * j;
                    int nC = cur[1] + d[i][1] * j;
                    if(nR <0 || nC < 0 || nR >= h || nC >= w) break;
                    if( map[count+1][nR][nC] == 0) continue;
                    deque.offer(new int[]{nR, nC});
                }
            }
        }
        gravity(count+1);
        return bP;
    }
    public static void gravity(int mapIndex){
        for(int i = 0; i < w; i++){
            for(int j = h-1; j >= 0; j--){
                if(map[mapIndex][j][i] == 0){
                    int nowR = j-1;
                    boolean check = false;
                    while(nowR >= 0){
                        if(map[mapIndex][nowR][i] != 0){
                            check = true;
                            map[mapIndex][j][i] = map[mapIndex][nowR][i];
                            map[mapIndex][nowR][i] = 0;
                            break;
                        }
                        nowR--;
                    }
                    if(!check) break;
                }
            }
        }
    }
    static int check(int index, int count){
        for(int i = 0; i < h; i++){
            if(map[count][i][index] != 0) return i;
        }
        return -1;
    }
}
