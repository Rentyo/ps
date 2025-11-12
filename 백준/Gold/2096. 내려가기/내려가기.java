import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[n][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] maxDP = new int[n][3];
        int[][] minDP = new int[n][3];

        for(int j = 0; j < 3; j++){
            maxDP[0][j] = arr[0][j];
            minDP[0][j] = arr[0][j];
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < 3; j++){
                maxDP[i][j] = maxDP[i-1][j];
                minDP[i][j] = minDP[i-1][j];
                if(j-1 >= 0){
                    maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i-1][j-1]);
                    minDP[i][j] = Math.min(minDP[i][j], minDP[i-1][j-1]);
                }
                if(j+1 < 3){
                    maxDP[i][j] = Math.max(maxDP[i][j], maxDP[i-1][j+1]);
                    minDP[i][j] = Math.min(minDP[i][j], minDP[i-1][j+1]);
                }
                maxDP[i][j] += arr[i][j];
                minDP[i][j] += arr[i][j];
            }
        }
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < 3; i++){
            max = Math.max(max, maxDP[n-1][i]);
            min = Math.min(min, minDP[n-1][i]);
        }
        System.out.println(max + " " + min);


    }
}
