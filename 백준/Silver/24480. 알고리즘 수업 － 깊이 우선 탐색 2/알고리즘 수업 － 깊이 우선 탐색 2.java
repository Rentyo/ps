import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb;
    static List<Integer>[] edge;
    static int[] seq;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        edge = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            edge[i] = new ArrayList<>();
        }
        seq = new int[N+1];
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edge[a].add(b);
            edge[b].add(a);
        }

        for(int i = 0; i <= N; i++){
            Collections.sort(edge[i]);
        }
        sb = new StringBuilder();
        seq[start] = ++count;
        dfs(start);
        for(int i = 1; i<= N; i++){
            sb.append(seq[i]).append("\n");
        }
        System.out.println(sb);
    }
    public static void dfs(int node){

        for(int i = edge[node].size()-1; i >= 0 ; i-- ){
            int next = edge[node].get(i);
            if(seq[next] != 0) continue;
            seq[next] = ++count;
            dfs(next);
        }   
    }
}
