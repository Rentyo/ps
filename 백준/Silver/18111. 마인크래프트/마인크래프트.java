import java.io.*;
import java.util.*;
public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        map = new int[n][m]; int min = 256;
        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        int result = Integer.MAX_VALUE;
        int maxHeight = -1;
        for(int h = min; h <= max; h++){
            int time = simulate(h, b);
            if(time <= result){
                result = time; maxHeight = h;
            }
        }
        System.out.println(result + " " + maxHeight);
    }
    public static int simulate(int height, int block){
        int time = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if (map[i][j] > height) {
                    int diff = map[i][j] - height;
                    block += diff;
                    time += diff * 2;
                } else {
                    int diff = height - map[i][j];
                    block -= diff;
                    time += diff;
                }
            }
        }
        if(block < 0){
            return Integer.MAX_VALUE;
        }
        return time;
    }
}