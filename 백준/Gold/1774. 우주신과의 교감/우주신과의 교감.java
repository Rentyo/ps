import java.io.*;
import java.util.*;
public class Main {
    static int[] root;
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y; 
        }
    }
    static class Road implements Comparable<Road>{
        int god1;
        int god2;
        double dist;
        public Road(int god1, int god2, double dist){
            this.god1 = god1;
            this.god2 = god2;
            this.dist = dist;
        }
        @Override
        public int compareTo(Road o){
            if(this.dist > o.dist) return 1;
            else if(this.dist < o.dist) return -1;
            else return 0;

        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Pos[] positions = new Pos[N+1];
        root = new int[N+1];
        for(int i = 1; i <= N; i++){
            root[i] = i;
            st = new StringTokenizer(br.readLine());
            positions[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Road> pq = new PriorityQueue<Road>();
        for(int i = 1; i <= N; i++){
            Pos pos1 = positions[i];
            for(int j = i+1; j <= N; j++){
                Pos pos2 = positions[j];
                double dist = Math.sqrt(Math.pow(pos1.x - pos2.x , 2) + Math.pow(pos1.y - pos2.y, 2));
                pq.offer(new Road(i, j, dist));
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        double sum = 0;
        while(pq.size() > 0){
            Road cur = pq.poll();
            if(find(cur.god1) == find(cur.god2)) continue;

            union(cur.god1, cur.god2);
            sum+= cur.dist;
        }
        System.out.printf("%.2f",sum);
        
    }
    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA < rootB) root[rootB] = rootA;
        else root[rootA] = rootB;
    }
    public static int find(int a){
        if(a == root[a]) return a;
        return root[a] = find(root[a]);   
    }
    
}