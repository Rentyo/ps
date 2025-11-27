import java.util.*;
import java.io.*;
 

class Main
{
    static BufferedReader br;
    static StringTokenizer st;
    static int v;
    static int e;
    // 맥도날드 조건
    static int tM;
    // 스타벅스 조건
    static int tS;
    static ArrayList<Place>[] edges;
    // 0 : 맥도날드, 1 : 스타벅스
    static int[][] minWeight;

    static Set<Integer> Macs = new HashSet<>();
    static Set<Integer> Stars = new HashSet<>();
    static class Place implements Comparable<Place>{
        int index;
        int weight;
        public Place(int index , int weight){
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Place o){
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        init();
        int min = Integer.MAX_VALUE;
        DijkstraMac();
        DijkstraStar();
        for(int i = 0; i < v; i++){
            if(minWeight[0][i] != 0 && minWeight[1][i] != 0 && minWeight[0][i] <= tM && minWeight[1][i] <= tS) min = Math.min(min, minWeight[0][i] + minWeight[1][i]);
        }
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);

    }
    static void init() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        edges = new ArrayList[v];
        minWeight = new int[2][v];
        for(int i = 0; i < v; i++){
            edges[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());
            edges[start].add(new Place(end, weight));
            edges[end].add(new Place(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int nM = Integer.parseInt(st.nextToken());
        tM = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < nM; i++){
            Macs.add(Integer.parseInt(st.nextToken())-1);
        }
        st = new StringTokenizer(br.readLine());
        int nS = Integer.parseInt(st.nextToken());
        tS = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < nS; i++){
            Stars.add(Integer.parseInt(st.nextToken())-1);
        }
    }    

    static void DijkstraMac(){
        PriorityQueue<Place> pq = new PriorityQueue<>();
        for(Integer i : Macs){
            pq.offer(new Place(i, 0));
        }
        boolean[] visited = new boolean[v];
        while(pq.size() > 0){
            Place cur = pq.poll();

            if(visited[cur.index]) continue;
            visited[cur.index] = true;
            minWeight[0][cur.index] = cur.weight;

            for(int i = 0; i < edges[cur.index].size(); i++){
                pq.offer(new Place(edges[cur.index].get(i).index, edges[cur.index].get(i).weight + cur.weight ));
            }
        }

        
    }
    static void DijkstraStar(){
        PriorityQueue<Place> pq = new PriorityQueue<>();
        for(Integer i : Stars){
            pq.offer(new Place(i, 0));
        }
        boolean[] visited = new boolean[v];
        while(pq.size() > 0){
            Place cur = pq.poll();

            if(visited[cur.index]) continue;
            visited[cur.index] = true;
            minWeight[1][cur.index] = cur.weight;

            for(int i = 0; i < edges[cur.index].size(); i++){
                pq.offer(new Place(edges[cur.index].get(i).index, edges[cur.index].get(i).weight + cur.weight ));
            }
        }
        
    }

}

