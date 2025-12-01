import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 한 칸에 서른개의 숫자를 넣을 수 있다.
        int[] v = new int[1_118_482];
        StringBuilder sb = new StringBuilder();
        while(st.hasMoreTokens()){
            int n = Integer.parseInt(st.nextToken());
            if( (v[n/30] & (1 << n%30)) == 0 ){
                sb.append(n).append(" ");
                v[n/30] |= (1 << (n%30));
            }
        }
        System.out.println(sb);
    }
}
