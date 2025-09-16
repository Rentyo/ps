import java.io.*;
import java.util.*;

public class Main {
    static final long mod = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long[] factorial = new long[N+1];
        factorial[0] =1;
        long result = 1;
        for(int i = 1; i<= N; i++){
            factorial[i] = result = result*i % mod;
        }

        System.out.println(factorial[N] * divide_merge(factorial[N-R]*factorial[R] % mod, mod-2) % mod);
    }
    public static long divide_merge(long a, long b){
        if(b == 0) return 1;
        if(b == 1) return a;

        long d = divide_merge(a, b/2);
        long result = d * d % mod;
        if(b % 2 == 1) result = result * a % mod;
        return result; 
    }
}