import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static char[][] map;
    public static int[][] d = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    static class Pos {
        int row;
        int col;
        int preRow;
        int preCol;
        int time;

        public Pos(int row ,int col, int preRow, int preCol, int time) {
            this.time = time;
            this.preCol = preCol;
            this.preRow = preRow;
            this.col = col;
            this.row = row;


        }
    }
    static HashSet<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new HashSet<>();
        Queue<Pos> queue = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'S'){
                    Pos pos = new Pos(i, j, i, j, 0);
                    map[i][j] = '0';
                    queue.offer(pos);
                }
            }
        }

        while(!queue.isEmpty()){
            Pos pos = queue.poll();
            int row = pos.row;
            int col = pos.col;
            int preRow = pos.preRow;
            int preCol = pos.preCol;
            int time = pos.time;
            int temp = (row * 1000 + col) * 1000000;


            for(int i = 0; i < 4 ; i++){
                int nR = row + d[i][0];
                int nC = col + d[i][1];

                if(nR < 0 || nC < 0 || nR >= N || nC >= M || (nR == preRow && nC == preCol) || map[nR][nC] =='X')continue;
                if(map[nR][nC] == 'H'){
                    System.out.println(time+1);
                    return;
                }
                if(visited.contains(temp + nR * 1000 + nC)) continue;
                if((map[preRow][preCol]-'0') + (map[row][col]-'0') + (map[nR][nC]-'0') > K) continue;
                visited.add(temp + nR * 1000 + nC);
                queue.offer(new Pos(nR, nC, row, col, time + 1));
            }
        }
        System.out.println(-1);
    }

}