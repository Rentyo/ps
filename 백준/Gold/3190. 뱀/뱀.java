import java.util.*;
import java.io.*;
public class Main {
    public static int[] dy = {-1, 0 , 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    static int [][] map;
    static ArrayList<Direction> dirs = new ArrayList<>();
    static class Direction {
        int time;
        char dir;
        public Direction(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }

        public int getTime(){
            return this.time;
        }
        public char getDir(){
            return this.dir;
        }

    }
    static class Position {
        int y;
        int x;
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY(){
            return this.y;
        }
        public int getX(){
            return this.x;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        // 맵의 넓이
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        // 사과의 개수
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            map[col-1][row-1] = 2;
        }
        // 방향 변환 횟수
        int L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < L ; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            dirs.add(new Direction(time,dir));
        }
        gameStart();

    }
    public static void gameStart(){
        int dir = 1;
        int index = 0;
        int time = 0;
        int startcol = 0;
        int startrow = 0;
        map[0][0] = 1;
        Queue<Position> q = new LinkedList<>();
        q.add(new Position(0,0));
        while(true){
            // 시간이 내가 넣은 방향 변환 횟수에 포함되어 있냐
            if(index < dirs.size() && time == dirs.get(index).getTime() ){
                dir += dirs.get(index).getDir() == 'D' ? 1 : -1;

                if(dir == -1){
                    dir = 3;
                }else if(dir == 4){
                    dir = 0;
                }
                index++;
            }
            time++;
            if(startcol + dy[dir] < 0 || startcol + dy[dir] >= map.length || startrow + dx[dir] < 0 || startrow + dx[dir] >= map.length || map[startcol + dy[dir]][startrow + dx[dir]] == 1){
                System.out.println(time);
                break;
            } else {
                if(map[startcol + dy[dir]][startrow + dx[dir]] == 0){
                    Position del = q.poll();
                    map[del.y][del.x] = 0;
                }
                map[startcol + dy[dir]][startrow + dx[dir]] = 1;
                startcol += dy[dir];
                startrow += dx[dir];
                q.offer(new Position(startcol,startrow));
            }
            // 1. 내가 이동하는 칸이 내 몸통 또는 벽이냐
            // 2. 내가 이동하는 칸에 사과가 있는지에 대한 여부

        }
    }
}
