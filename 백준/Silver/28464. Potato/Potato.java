import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < N/2; i++){
            countA += arr[i];
        }
        for(int i = N/2; i < N; i++){
            countB += arr[i];
        }
        sb.append(countA).append(" ").append(countB);
        System.out.println(sb);
    }    
    
}
