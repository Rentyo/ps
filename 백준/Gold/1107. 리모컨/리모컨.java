
import java.io.*;
import java.util.*;
public class Main{
    static int v;
    static int min;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
      
        v = 0;
        if(M > 0){  
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++){
                v |= (1 << Integer.parseInt(st.nextToken()));
            }
        }
        min = Math.abs(N-100);
        brute_force(0, 0);
        System.out.println(min);
    }
    static void brute_force(int count, int sum){
        if(count > 0){ 
            min = Math.min(min, Math.abs(sum - N) + count);
        }
        
        if(count == 7) return;  
        
        for(int i = 0; i <= 9; i++){
            if((v & (1 << i)) != 0) continue;
            brute_force(count + 1, sum * 10 + i);
        }
    }
}