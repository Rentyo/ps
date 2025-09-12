import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    static class Cell implements Comparable<Cell>{
        int row;
        int col;
        int life;
        int now;
        int time;
        public Cell(int row, int col, int life, int time){
            this.row = row;
            this.col = col;
            this.life = life;
            this.now = 0;
            this.time = time;
        }
        @Override
        public int compareTo(Cell o){
            if(this.time == o.time) return -1*Integer.compare(this.life, o.life);
            else return Integer.compare(this.time, o.time);
        }
    }
    static PriorityQueue<Cell> cells;
    static int[][] map;
    static int size;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            size = 1000;
            map = new int[size][size];
            int inputrow = Integer.parseInt(st.nextToken());
            int inputcol = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            cells = new PriorityQueue<Cell>();
            for(int i = 500 ; i < 500 + inputrow; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 500; j < 500 + inputcol; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > 0){
                        cells.offer(new Cell(i, j, map[i][j],1));
                        map[i][j] = 1;
                    }
                }
            }
            simulate(time);
            sb.append(cells.size()).append("\n");
        }
        System.out.print(sb);
    }
    static void simulate(int time){
        while(cells.size() > 0){
            Cell cur = cells.poll(); 
            cur.now++;
            if(cur.time > time) {
                cells.offer(cur);
                break;
            }
            cur.time++;
            if(cur.now == 1 && map[cur.row][cur.col] == 2){
                for(int j = 0; j < 4; j++){
                    int nR = cur.row + d[j][0];
                    int nC = cur.col + d[j][1];
                    if(map[nR][nC] != 0) continue;
                    cells.offer(new Cell(nR, nC, cur.life, cur.time));
                    map[nR][nC] = 1;
                }
            }
            if(cur.now == cur.life){
                cur.now = 0;
                map[cur.row][cur.col]++;
            }
            if(map[cur.row][cur.col] != 3) cells.offer(cur);
        }
    }
}