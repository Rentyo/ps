import java.io.*;
import java.util.*;
public class Solution {
    static class Island{
        int x;
        int y;
        public Island(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Bridge implements Comparable<Bridge> {
        int s;
        int e;
        double w;
        public Bridge(int s, int e, double w){
            this.s =s;
            this.e = e;
            this.w =w;
        }
        @Override
        public int compareTo(Bridge o){
            if(this.w > o.w) return 1;
            else if(this.w < o.w) return -1;
            else return 0;
        }
    }
    static int[] root;
    static int n;
    static ArrayList<Bridge> dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st1;
        StringTokenizer st2;
        StringBuilder sb = new StringBuilder();
        for(int t = 1; t <= T; t++){
            sb.append("#").append(t).append(" ");

            ArrayList<Island> islands = new ArrayList<>();
            dist = new ArrayList<>();
            n = Integer.parseInt(br.readLine());
            root = new int[n];
            for(int i = 0; i < n; i++){
                root[i] = i;
            }
            st1 = new StringTokenizer(br.readLine());
            st2 = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                islands.add(new Island(Integer.parseInt(st1.nextToken()),Integer.parseInt(st2.nextToken())));
            }
            double E = Double.parseDouble(br.readLine());


            for(int i = 0; i < islands.size(); i++){
                for(int j = i+1; j< islands.size(); j++){
                    double weight = E *(Math.pow(Math.abs(islands.get(i).x - islands.get(j).x),2) + Math.pow(Math.abs(islands.get(i).y - islands.get(j).y),2) ); 
                    dist.add(new Bridge(i, j, weight));
                }
            }

            Collections.sort(dist);

            int vCnt = 0;
            int index = 0;
            double sum = 0;
            while(vCnt != n-1){
                Bridge now = dist.get(index++);
                int nodeA = now.s;
                int nodeB = now.e;

                if(find(nodeA) == find(nodeB)) continue;
                union(nodeA, nodeB);
                sum += now.w;
                vCnt++;
            }
            sb.append(Math.round(sum)).append("\n");
        }
        System.out.print(sb);
    }
    private static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA > rootB) root[rootA] = rootB;
        else root[rootB] = rootA;
    }
    private static int find(int a){
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }
}
