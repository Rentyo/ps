import java.util.*;
import java.io.*;
class Main
{
    static int n;
    static int m;
    static int r;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        rotate(r);
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    public static void rotate(int r){
        int rowStart = 0;
        int rowEnd = n-1;
        int colStart = 0;
        int colEnd = m-1;
        while(rowStart < rowEnd && colStart < colEnd){
            List<Integer> list = new ArrayList<>();
            int row = rowStart;
            int col = colStart;
            int dir = 0;
            for(int j = 0; j < 2*(rowEnd-rowStart) + 2*(colEnd-colStart); j++){
                list.add(map[row][col]);
                switch(dir){
                    case 0 :
                        col++;
                        if(col == colEnd){
                            dir++;
                        }
                        break;
                    case 1 :
                        row++;
                        if(row == rowEnd){
                            dir++;
                        }
                        break;
                    case 2 :
                        col--;
                        if(col == colStart){
                            dir++;
                        }
                        break;
                    case 3 :
                        row--;
                        break;
                    default : break;
                } 
            }
            for(int i = 0; i < r % (2* (rowEnd-rowStart) + 2*(colEnd-colStart)); i++){
                list.add(list.remove(0));
            }
            dir = 0;
            row = rowStart;
            col = colStart;
            for(int j = 0; j < 2*(rowEnd-rowStart) + 2*(colEnd-colStart); j++){
                map[row][col] = list.get(j);
                switch(dir){
                    case 0 :
                        col++;
                        if(col == colEnd){
                            dir++;
                        }
                        break;
                    case 1 :
                        row++;
                        if(row == rowEnd){
                            dir++;
                        }
                        break;
                    case 2 :
                        col--;
                        if(col == colStart){
                            dir++;
                        }
                        break;
                    case 3 :
                        row--;
                        break;
                    default : break;
                } 
            }
            rowStart++;
            colStart++;
            rowEnd--;
            colEnd--;
        }
    }
}