import java.io.*;
import java.util.*;
public class Solution {
    static long[] fact;
    static final int mod = 1234567891;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        fact = new long[1000001];
        fact[0] = 1;
        long result = 1;
        for(int i = 1; i <= 1000000; i++){
            fact[i] = result = result* i % mod;
        }


        for(int test_case = 1; test_case <= t; test_case++) {
            sb.append("#").append(test_case).append(" ");
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long cal = fact[N] * divide_merge(fact[N-R] * fact[R] % mod , mod-2) % mod;
            sb.append(cal).append("\n");
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
}
     

