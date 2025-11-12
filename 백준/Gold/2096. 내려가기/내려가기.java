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
            maxDP[i][0] = Math.max(maxDP[i-1][0], maxDP[i-1][1]) + arr[i][0];
            minDP[i][0] = Math.min(minDP[i-1][0], minDP[i-1][1]) + arr[i][0];
            maxDP[i][1] = Math.max(Math.max(maxDP[i-1][0] , maxDP[i-1][2]), maxDP[i-1][1]) + arr[i][1];
            minDP[i][1] = Math.min(Math.min(minDP[i-1][0] , minDP[i-1][2]), minDP[i-1][1]) + arr[i][1]; 
            maxDP[i][2] = Math.max(maxDP[i-1][2], maxDP[i-1][1]) + arr[i][2];
            minDP[i][2] = Math.min(minDP[i-1][2], minDP[i-1][1]) + arr[i][2];
        }
        System.out.println(Math.max(maxDP[n-1][0], Math.max(maxDP[n-1][1], maxDP[n-1][2])) + " " + Math.min(minDP[n-1][0], Math.min(minDP[n-1][1], minDP[n-1][2])));


    }
}
