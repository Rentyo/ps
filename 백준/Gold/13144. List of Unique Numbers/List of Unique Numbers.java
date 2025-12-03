import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] now = new int[100_001];
        int start = 0;
        int end = 0;
        long result = 0;
        while(start < N && end < N){
            if(now[arr[end]] != 0){
                now[arr[start]]--;
                start++;
            }else{
                now[arr[end]]++;
                end++;
                result += (end-start);
            }
        }
        System.out.println(result);
    }
}
