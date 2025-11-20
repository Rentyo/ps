import java.io.*;
import java.util.*;
public class Main {

    static class Travel implements Comparable<Travel>{
        int node;
        int dist;
        int count;
        StringBuilder sb;
        
        public Travel(int node, int dist, int count){
            this.node = node;
            this.dist = dist;
            this.count = count;
            sb = new StringBuilder();
        }
        @Override
        public int compareTo(Travel o){
            return Integer.compare(this.dist, o.dist);
        }
    }
    static class Bus{
        int nextNode;
        int diff;
        public Bus(int nextNode, int diff){
            this.nextNode = nextNode;
            this.diff = diff;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N= Integer.parseInt(br.readLine());
        ArrayList<Bus>[] edges = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            edges[i] = new ArrayList<>();
        }
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Bus(b,c));
        }
        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[N+1];
        Arrays.fill(dist, 100_000_000);

        PriorityQueue<Travel> pq = new PriorityQueue<>();
        pq.offer(new Travel(start, 0, 1));

        while(!pq.isEmpty()){
            Travel cur = pq.poll();
            if(dist[cur.node] <= cur.dist) continue;
            dist[cur.node] = cur.dist;
            cur.sb.append(cur.node).append(" ");
            if(cur.node == end){
                System.out.println(cur.dist);
                System.out.println(cur.count);
                System.out.println(cur.sb);
                return;
            }
            for(int i = 0; i < edges[cur.node].size(); i++){
                int nextNode = edges[cur.node].get(i).nextNode;
                int diff = edges[cur.node].get(i).diff;

                if(dist[nextNode] <= cur.dist + diff) continue;
                Travel next = new Travel(nextNode, cur.dist+diff , cur.count+1);
                next.sb.append(cur.sb);
                pq.offer(next);
            }
        }
    }
}
