import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] bigger = new int[N+1][N+1];
        int[][] smaller = new int[N+1][N+1];

        for(int i = 0; i <= N; i++){
            Arrays.fill(bigger[i], 100);
            Arrays.fill(smaller[i], 100);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());
            bigger[small][big] = 1;
            smaller[big][small] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i =1; i <= N; i++){
                for(int j=1; j<= N; j++){
                    bigger[i][j] = Math.min(bigger[i][j], bigger[i][k] + bigger[k][j]);
                    smaller[i][j] = Math.min(smaller[i][j], smaller[i][k] + smaller[k][j]);
                }
            }
        }
        int count = 0;

        int[] big = new int[N+1];
        int[] small = new int[N+1];
        for(int i = 1; i <= N; i++){
            for(int j =1; j <= N; j++){
                if(bigger[i][j] != 100){
                    big[i]++;
                }
                if(smaller[i][j] != 100){
                    small[i]++;
                }
            }
            if(big[i] > N/2 || small[i] > N/2) count++; 
        }
        
        System.out.println(count);

    }
    
}