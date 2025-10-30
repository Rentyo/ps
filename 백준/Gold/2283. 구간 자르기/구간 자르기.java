import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] sum = new int[1_000_001];

        int max = 0;
        int min = 1_000_001;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sum[start]++;
            sum[end]--;
            max = Math.max(end, max);
            min = Math.min(start, min);
        }
        for(int i = min+1; i <= max; i++){
            sum[i] += sum[i-1];
        }

        int s_p = 0;
        int e_p = min;
        int result = 0;
        while(s_p <= e_p && e_p <= max ){
            if(result == K){
                System.out.println(s_p + " " + e_p);
                return;
            }else if(result > K){
                result -= sum[s_p++];
            }else{
                result += sum[e_p++];
            }
        }
        System.out.println(0 + " " + 0);
        
    }
}