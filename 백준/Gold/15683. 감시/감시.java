import java.io.*;
import java.util.*;
public class Main {
    static final int[][] d = {{1,0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int[][][] directions = {
        {}, 
        {{1}, {2}, {3}, {0}}, 
        {{1,3}, {0 , 2}},
        {{0,1}, {1, 2}, {2, 3}, {0, 3}},
        {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
        {{0,1,2,3}}
    };
    static class CCTV{
        int col;
        int row;
        int type;
        public CCTV(int row, int col, int type){
            this.col = col;
            this.row = row;
            this.type = type;
        }
    }
    static int[][] check;
    static int n;
    static int m;
    static int min;
    static ArrayList<CCTV> cctvs;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new int[n][m];
        cctvs = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int w = Integer.parseInt(st.nextToken());
                if(w != 0 && w != 6){
                    cctvs.add(new CCTV(i, j, w));
                    check[i][j] = -1;
                }else{
                    check[i][j] = w;
                }
            }
        }
        min = n*m +1;
        permutation(0);
        System.out.println(min);
    }

    static void permutation(int index){
        if(index == cctvs.size()){
            calculate();
            return;
        }

        int size = directions[cctvs.get(index).type].length;
        for(int i = 0; i < size; i++){
            simulate(index, i, -1);
            permutation(index+1);
            simulate(index, i, 1);
        }

    }

    static void simulate(int index, int dir, int cal){
        CCTV cctv = cctvs.get(index);
        int[] dirs = new int[directions[cctv.type][dir].length];
        for(int i = 0; i < directions[cctv.type][dir].length; i++){
            dirs[i] = directions[cctv.type][dir][i];
        }

        for(int i = 0; i < dirs.length; i++){
            int startRow = cctv.row +d[dirs[i]][0];
            int startCol = cctv.col +d[dirs[i]][1];
            while(startCol >= 0 && startCol < m && startRow >=0 && startRow < n){
                if(check[startRow][startCol] ==6 ) break;
                check[startRow][startCol] += cal;
                startRow += d[dirs[i]][0];
                startCol += d[dirs[i]][1];
            } 
        }
    }
    static void calculate(){
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m ; j++ ){
                if(check[i][j] == 0) count++;
            }
        }
        min = Math.min(min, count);
    }
    
}
