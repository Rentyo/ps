
import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder sb;
    static int n;
    static ArrayList<Integer>[] edges;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken())-1;
        edges = new ArrayList[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            edges[x].add(y);
            edges[y].add(x);
        }
        for(int i = 0; i < n; i++){
            Collections.sort(edges[i]);
        }
        sb = new StringBuilder();
        boolean[] visited = new boolean[n];
        dfs(start, visited);
        sb.append("\n");
        visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        sb.append(start+1).append(" ");
        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i = 0; i < edges[cur].size(); i++){
                if(!visited[edges[cur].get(i)]){
                    visited[edges[cur].get(i)] = true;
                    q.offer(edges[cur].get(i));
                    sb.append(edges[cur].get(i) +1).append(" ");
                }
            }
        }
        System.out.println(sb);
    }
    static void dfs(int start, boolean[] visited){
        sb.append(start+1).append(" ");
        visited[start] = true;
        for(int i = 0; i < edges[start].size(); i++){
            if(!visited[edges[start].get(i)]){
                dfs(edges[start].get(i), visited);
            }
        }

    }
}
