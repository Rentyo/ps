import java.io.*;
import java.util.*;
public class Main {
    static class Color{
        int node;
        boolean color;

        public Color(int node, boolean color){
            this.node = node;
            this.color = color;
        }
    }
    static int[] colors;
    static ArrayList<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            edges = new ArrayList[V+1];
            colors = new int[V+1];
            for(int j = 1; j <= V ;j++){
                edges[j] = new ArrayList<>();
            }
            for(int j = 0; j < E; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edges[a].add(b);
                edges[b].add(a);
            }
            boolean result = true;
            for(int j = 1; j <= V; j++){
                if(edges[j].size() != 0 && colors[j] == 0){
                    if(!check(j)){
                        result = false;
                        break;
                    };
                }
            }
            sb.append(result ? "YES" : "NO").append("\n"); 
        }
        System.out.println(sb);
    }
    public static boolean check(int start){
        Queue<Color> queue = new ArrayDeque<>();
        queue.offer(new Color(start, true));
        colors[start] = 1;
        while(queue.size() > 0){
            Color now = queue.poll();
            int node = now.node;
            int color = now.color ? 1 : 2;

            for(int i = 0; i < edges[node].size(); i++){
                int nextNode = edges[node].get(i);
                if(colors[nextNode] == 0){
                    queue.offer(new Color(nextNode, !now.color));
                    colors[nextNode] = now.color ? 2 : 1;
                }else{
                    if(colors[nextNode] == color) return false;
                }
            }
        }

        return true;
    }
}