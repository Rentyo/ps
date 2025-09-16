import java.io.*;
import java.util.*;
public class Main {
    static class StarLine implements Comparable<StarLine>{
        int star1;
        int star2;
        double dist;
        public StarLine(int star1, int star2, double dist){
            this.star1 = star1;
            this.star2 = star2;
            this.dist = dist;
        }

        @Override
        public int compareTo(StarLine o){
            return Double.compare(this.dist, o.dist);
        }
    }

    static int[] root;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[][] star = new double[2][n];
        root = new int[n];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            root[i] = i;
            st = new StringTokenizer(br.readLine());
            star[0][i] = Double.parseDouble(st.nextToken());
            star[1][i] = Double.parseDouble(st.nextToken());
        }
        PriorityQueue<StarLine> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                pq.offer( new StarLine(i, j ,Math.sqrt(Math.pow(star[0][i] - star[0][j],2) + Math.pow(star[1][i] - star[1][j],2)))); 
            }
        }

        double result = 0.0;
        while(pq.size() > 0){
            StarLine cur = pq.poll();

            if(find(cur.star1) == find(cur.star2)) continue;
            union(cur.star1, cur.star2);
            result+= cur.dist;
        }
        System.out.println(result);
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA > rootB) root[rootA] = rootB;
        else root[rootB] = rootA; 
    }
    static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }
}