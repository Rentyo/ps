import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], 123456789);
        }
        for(int i = 0; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            dist[p1][p2] = 1;
            dist[p2][p1] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i =1; i <=N; i++){
                for(int j =1; j <= N; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int min = 1234567890;
        int result = 0;
        for(int i = 1; i <= N; i++){
            int count = 0;
            for(int j = 1; j <= N; j++){
                count+= dist[i][j];
            }
            if(count < min){
                min = count;
                result = i;
            }
        }
        System.out.println(result);
        

    }
}
