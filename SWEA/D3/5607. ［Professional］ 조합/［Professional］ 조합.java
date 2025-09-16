import java.io.*;
import java.util.*;
public class Solution {
    static final int mod = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= t; test_case++) {
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long result = factorial(N) * divide_merge(factorial(N-R) * factorial(R) % mod , mod-2) % mod;
            sb.append(result).append("\n");
        }
        System.out.println(sb);  
    }
    public static long divide_merge(long a, int b){
        if(b == 0) return 1;
        if(b == 1) return a;
        long d = divide_merge(a, b/2);
        long result = d * d % mod;
        if(b % 2 == 1) result = result * a % mod;
        return result;
    }
    public static long factorial(int n){
        
        long total = 1;
        for(int i = 2; i <= n; i++){
            total = i * total % mod;
        }

        return total;
    }
}
     

