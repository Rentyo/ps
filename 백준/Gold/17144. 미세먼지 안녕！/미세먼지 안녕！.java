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
    static int[][] map;
    static int row;
    static int col;
    static int time;
    static AirCondition[] airs;     
    public static class AirCondition {
        int row;
        int col;
        public AirCondition(int row, int col ){
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        airs = new AirCondition[2];

        int index = 0;
        for(int i = 0; i < row; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j < col; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    airs[index++] = new AirCondition(i, j);
                }
            }
        }
        for(int i = 0; i < time; i++){
            spread();
            rotate();
        }
        int sum = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(map[i][j] > 0) sum += map[i][j];
            }
        }
        System.out.print(sum);
    }


    public static void spread(){
        int[][] temp = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j< col; j++){
                if(map[i][j] < 5) {
                    temp[i][j] += map[i][j];
                    continue;
                }
                int num = map[i][j];
                for(int k = 0; k < 4; k++){
                    int nR = i + d[k][0];
                    int nC = j + d[k][1];
                    if(nR < 0 || nC < 0 || nR >= row || nC >= col || map[nR][nC] == -1) continue;
                    temp[nR][nC] += map[i][j] / 5;
                    num-= map[i][j] /5;
                }
                temp[i][j] += num;
            }
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void rotate(){
        Deque<Integer> list1 = new ArrayDeque();
        Deque<Integer> list2 = new ArrayDeque();
        int startRow1 = 0;
        int endRow1 = airs[0].row;
        int startRow2 = airs[1].row;
        int endRow2 = row-1;
        int startCol = airs[0].col;
        int endCol = col-1;


        int dir = 0;
        int simCol = startCol;
        int simRow = endRow1;
        for(int i = 0; i < 2*(endCol - startCol) + 2*(endRow1 - startRow1) ; i++){
            list1.offer(map[simRow][simCol] == -1 ? 0 :  map[simRow][simCol]);
            if(dir == 0){
                if(++simCol == col-1) dir++;
            }else if(dir == 1){
                if(--simRow == 0) dir++;
            }else if(dir == 2){
                if(--simCol == 0) dir++;
            }else if(dir == 3){
                ++simRow;
            }
        }
        
        list1.pollLast();
        list1.offerFirst(0);
        dir = 0; 
        simCol = startCol;
        simRow = startRow2;
        for(int i = 0; i < 2*(endCol - startCol) + 2*(endRow2 - startRow2) ; i++){
            list2.offer(map[simRow][simCol] == -1 ? 0 : map[simRow][simCol] );
            if(dir == 0){
                if(++simCol == col-1) dir++;
            }else if(dir == 1){
                if(++simRow == row-1) dir++;
            }else if(dir == 2){
                if(--simCol == 0) dir++;
            }else if(dir == 3){
                --simRow;
            }
        }
        list2.pollLast();
        list2.offerFirst(0);
        dir = 0;
        simCol = startCol;
        simRow = endRow1;
        for(int i = 0; i < 2*(endCol - startCol) + 2*(endRow1 - startRow1) ; i++){
            map[simRow][simCol] = list1.poll();
            if(dir == 0){
                if(++simCol == col-1) dir++;
            }else if(dir == 1){
                if(--simRow == 0) dir++;
            }else if(dir == 2){
                if(--simCol == 0) dir++;
            }else if(dir == 3){
                ++simRow;
            }
        }
        dir = 0; 
        simCol = startCol;
        simRow = startRow2;
        for(int i = 0; i < 2*(endCol - startCol) + 2*(endRow2 - startRow2) ; i++){
            map[simRow][simCol] = list2.poll();
            if(dir == 0){
                if(++simCol == col-1) dir++;
            }else if(dir == 1){
                if(++simRow == row-1) dir++;
            }else if(dir == 2){
                if(--simCol == 0) dir++;
            }else if(dir == 3){
                --simRow;
            }
        }
        map[airs[0].row][airs[0].col] = -1;
        map[airs[1].row][airs[1].col] = -1;
    }
}