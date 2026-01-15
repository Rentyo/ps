import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    static int[][] d ={
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1){
                    map[i][j] = 0;
                    list.add(dfs(i, j));
                }
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int i = 0; i < list.size(); i++){
            sb.append(list.get(i)).append("\n");
        }
        System.out.println(sb);

    }

    public static int dfs(int row, int col){

        int count = 1;
        for(int i = 0; i < 4; i++){
            int nR =  row + d[i][0];
            int nC = col + d[i][1];
            if(nR >= 0 && nC >= 0 && nR < map.length && nC < map[0].length && map[nR][nC] == 1){
                map[nR][nC] = 0;
                count += dfs(nR, nC);
            }
        }
        return count;
    }

}
