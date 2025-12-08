import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int floor = (N % H == 0) ? H : N % H;
            int room = (N % H == 0) ? N / H : N / H + 1;

            sb.append(floor);
            if(room < 10) sb.append("0").append(room);
            else sb.append(room);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
