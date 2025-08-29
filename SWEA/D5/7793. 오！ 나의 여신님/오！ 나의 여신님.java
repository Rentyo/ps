import java.io.*;
import java.util.*;

public class Solution {
    static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    static boolean[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc= 1;tc<=T;tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            map = new boolean[2][N][M];
            //0 : 환경
            //1 : 사용자
            int[] dest = new int[2];
            int[] user = new int[4];
            List<int[]> devil = new ArrayList<>();
            for(int i=0;i<N;i++){
                String s = br.readLine();
                for(int j=0;j<M;j++){
                    char ch = s.charAt(j);
                    if(ch == 'D'){
                        dest[0] = i;
                        dest[1] = j;
                    }else if(ch == 'S'){
                        user[0] = i;
                        user[1] = j;
                        user[2] = 1;
                        user[3] = 0;
                    }else if(ch == '*'){
                        devil.add(new int[3]);
                        devil.get(devil.size()-1)[0] =i;
                        devil.get(devil.size()-1)[1] =j;
                    }else if(ch == 'X'){
                        map[0][i][j] = true;
                    }
                }
            }

            Deque<int[]> q = new LinkedList<>();
            for(int i = 0; i < devil.size(); i++){
                q.offer(devil.get(i));
                map[0][devil.get(i)[0]][devil.get(i)[1]] = true;
            }
            q.offer(user);
            map[1][user[0]][user[1]] = true;
            q.offer(user);
            int cnt = -1;
            while(!q.isEmpty()){
                int[] cur = q.poll();

                if(cur[2] == 0){
                    for(int i = 0; i < 4; i++){
                        int nR = cur[0] + d[i][0];
                        int nC = cur[1] + d[i][1];
                        if(nR < 0 || nC < 0 || nR >= N || nC >= M || map[0][nR][nC] || (nR == dest[0] && nC == dest[1])) continue;
                        map[0][nR][nC] = true;
                        q.offer(new int[]{nR, nC, 0});
                    }
                }else{
                    if(cur[0] == dest[0] && cur[1] == dest[1]){
                        cnt = cur[3];
                        break;
                    }
                    for(int i = 0; i < 4; i++){
                        int nR = cur[0] + d[i][0];
                        int nC = cur[1] + d[i][1];
                        if(nR < 0 || nC < 0 || nR >= N || nC >= M || map[0][nR][nC] || map[1][nR][nC]) continue;
                        map[1][nR][nC] = true;
                        q.offer(new int[]{nR, nC, 1, cur[3]+1});
                    }
                }
            }
            sb.append(cnt != -1 ? cnt : "GAME OVER").append("\n");
        }
        System.out.println(sb);
    }
}
