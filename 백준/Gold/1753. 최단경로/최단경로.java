import java.io.*;
import java.util.*;
public class Main {
    static int[] d;
    static boolean[] v;
    static ArrayList<ArrayList<Edge>> map;
    static class Edge implements Comparable<Edge>{
        int end;
        int weight;
        public Edge(int e, int w)
        {
            this.end = e;
            this.weight = w;
        }

        @Override
        public int compareTo(Edge o){
            return this.weight-o.weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vt = Integer.parseInt(st.nextToken());
        v = new boolean[vt];
        d = new int[vt];
        map = new ArrayList<>();
        for(int i  = 0; i < vt; i++){
            map.add(new ArrayList<>());
            d[i] = Integer.MAX_VALUE;
        }
        int ed = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        for(int i  =0 ; i < ed; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map.get(s-1).add(new Edge(e-1,w));
        }

        d[n-1] = 0;
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(n-1, 0));
        while(!q.isEmpty()){
            Edge e = q.poll();
            if(v[e.end]) continue;
            v[e.end] = true;
            for(int i = 0; i < map.get(e.end).size(); i++){
                if(d[map.get(e.end).get(i).end] > d[e.end] + map.get(e.end).get(i).weight){
                    d[map.get(e.end).get(i).end] = d[e.end] + map.get(e.end).get(i).weight;
                    q.offer(new Edge(map.get(e.end).get(i).end, d[map.get(e.end).get(i).end]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vt; i++){
            sb.append(d[i] == Integer.MAX_VALUE ? "INF" : d[i]).append("\n");
        }
        System.out.println(sb);

    }
}