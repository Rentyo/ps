import java.io.*;
import java.util.*;

public class Main {
    static int[] dist;
    static boolean[] v;
    static ArrayList<ArrayList<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t= Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int test_case  = 0; test_case < t; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map = new ArrayList<>();
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for(int i = 0; i < v; i++){
                map.add(new ArrayList<>());
            }
            for(int i = 0; i < e; i++){
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                map.get(v1-1).add(v2-1);
                map.get(v2-1).add(v1-1);
            }
            sb.append(v-1).append("\n");
        }
        System.out.println(sb);

    }
}