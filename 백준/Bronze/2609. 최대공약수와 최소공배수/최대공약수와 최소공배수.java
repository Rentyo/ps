import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        int gcd = GCD(A, B);
        int lcm = A * B / gcd;
        StringBuilder sb = new StringBuilder();
        sb.append(gcd).append("\n").append(lcm);
        System.out.println(sb);
    }
    
    public static int GCD(int a, int b){
        if(b == 0) return a;
        return GCD(b, a%b);
    }

}
