import java.util.*;

public class Main {
    public static int[] rootPlanet;
    public static PriorityQueue<Tunnel> pqTunnel = new PriorityQueue<Tunnel>();
    public static PriorityQueue<int[]>[] pqPoints = new PriorityQueue[3];
    static class Tunnel implements Comparable<Tunnel> {
        int planetA;
        int planetB;
        int dist;
        public Tunnel(int planetA, int planetB, int dist){
            this.planetA = planetA;
            this.planetB = planetB;
            this.dist = dist;
        }
        public int compareTo(Tunnel t){
            return this.dist - t.dist;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        rootPlanet = new int[n];
        for (int i = 0; i < 3; i++) {
            pqPoints[i] = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
        }
        for (int i = 0; i < n; i++) {
            rootPlanet[i] = i;
            for(int j = 0; j < 3; j++){
                pqPoints[j].add(new int[]{sc.nextInt(), i});
            }
        }
        for(int i=0; i<3; i++) {
            PriorityQueue<int[]> pqPoint = pqPoints[i];
            int size = n-1;
            while(size-->0) {
                int[] cur = pqPoint.poll();
                int[] next = pqPoint.peek();
                pqTunnel.add(new Tunnel(cur[1], next[1], Math.abs(cur[0]-next[0])));
            }
        }
        long ans = 0;
        int cnt = 0;
        int size = pqTunnel.size();
        while(size-->0) {
            Tunnel edge = pqTunnel.poll();
            if(find(edge.planetA) != find(edge.planetB)) {
                union(edge.planetA, edge.planetB);
                ans += edge.dist;
                if(++cnt == n-1) break;
            }
        }

        System.out.println(ans);



    }
    public static void union(int planetA, int planetB){
        int rootPlanetA = find(rootPlanet[planetA]);
        int rootPlanetB = find(rootPlanet[planetB]);
        if(rootPlanetA > rootPlanetB) rootPlanet[rootPlanetA] = rootPlanetB;
        else rootPlanet[rootPlanetB] = rootPlanetA;
    }
    public static int find(int num) {
        if(rootPlanet[num] == num) return num;
        return rootPlanet[num] = find(rootPlanet[num]);
    }
}