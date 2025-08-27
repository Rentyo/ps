
import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    static class Micro {
        int col;
        int row;
        int dir;
        int w;
        public Micro(int row, int col, int dir, int w) {
            this.col = col;
            this.row = row;
            this.dir = dir;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> dir_d =  new HashMap<>();
        dir_d.put(1, 2);
        dir_d.put(2, 0);
        dir_d.put(3, 3);
        dir_d.put(4, 1);
        StringTokenizer st;

        for(int t = 1; t <= T; t++){
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<Micro> micros = new ArrayList<>();
            Set<Integer>[][] map = new HashSet[n][n];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    map[i][j] = new HashSet<>();
                }
            }
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                int dir = dir_d.get(Integer.parseInt(st.nextToken()));
                micros.add(new Micro(row, col, dir, w));
                map[row][col].add(i);
            }

            for(int time = 1; time <= m; time++){
                for(int i = 0; i < micros.size(); i++){
                    Micro cur =  micros.get(i);
                    if(cur.w == 0)continue;
                    map[cur.row][cur.col].remove(i);

                    cur.row += d[cur.dir][0];
                    cur.col += d[cur.dir][1];

                    map[cur.row][cur.col].add(i);
                    if(cur.row == n-1 || cur.col == n-1 || cur.row == 0 || cur.col == 0){
                        cur.dir = (cur.dir+2)%4;
                        cur.w /=2;
                    }
                }
                for(int i = 0; i < micros.size(); i++){
                    if(micros.get(i).w == 0 || map[micros.get(i).row][micros.get(i).col].size() == 1) continue;
                    int max = -1;
                    int idx = -1;
                    int sum = 0;
                    for(Integer j : map[micros.get(i).row][micros.get(i).col]){
                        sum += micros.get(j).w;
                        if(max < micros.get(j).w){
                            if(idx != -1){
                                micros.get(idx).w = 0;
                            }
                            idx = j;
                            max = micros.get(j).w;
                        }else{
                            micros.get(j).w = 0;
                        }
                    }
                    micros.get(idx).w = sum;
                    map[micros.get(i).row][micros.get(i).col].clear();
                    map[micros.get(i).row][micros.get(i).col].add(idx);
                }
            }
            int sum = 0;
            for(int i = 0; i < micros.size(); i++){
                sum +=  micros.get(i).w;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}