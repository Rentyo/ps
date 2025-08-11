import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test_case  = 0; test_case < t; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for(int i = 0; i < e; i++){
                br.readLine();
            }
            sb.append(v-1).append("\n");
        }
        System.out.println(sb);

    }
}