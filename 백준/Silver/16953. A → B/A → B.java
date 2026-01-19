import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;
        while(N != M){
            if(N > M) {
                System.out.println(-1);
                return;
            }
            if(M % 10 == 1) M /= 10;
            else if(M % 2 == 0) M /= 2;
            else{
                System.out.println(-1);
                return;
            }

            cnt++;


        }
        System.out.println(cnt+1);


    }
}
