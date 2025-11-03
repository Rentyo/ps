import java.io.*;
import java.util.*;
public class Main {
    static int[][] dist;
    static int v;
    static int n;
    static int min;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        dist = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    dist[j][k] = Math.min(dist[j][k] , dist[j][i] + dist[i][k]);
                }
            }
        }
        v = 0;
        v |= 1 << start;
        min = Integer.MAX_VALUE;
        permutation(0, start, 1);
        System.out.println(min);
        
    }
    public static void permutation(int sum, int pre, int count){
        if(sum >= min) return;
        if(count == n){
            min = sum;
            return;
        }

        for(int i = 0; i < n; i++){
            if( (v & (1 << i)) == 0){
                v |= (1 << i);
                permutation(sum + dist[pre][i] , i, count+1);
                v &= ~(1 << i);
            }
        }
    }
}