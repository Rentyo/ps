import java.io.*;
import java.util.*;
public class Main {
    static int[] root;
    static int sP;
    static int eP;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int max = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return -1*Integer.compare(o1[2], o2[2]);
            }
        });
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken())-1;
            int node2 = Integer.parseInt(st.nextToken())-1;
            int value = Integer.parseInt(st.nextToken());
            max = Math.max(max, value);
            pq.offer(new int[]{node1, node2, value});
        }
        root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }
        st  = new StringTokenizer(br.readLine());
        sP =  Integer.parseInt(st.nextToken())-1;
        eP = Integer.parseInt(st.nextToken())-1;

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(find(cur[0]) == find(cur[1])) continue;
            union(cur[0], cur[1]);
            if(find(sP) == find(eP)) {
                System.out.println(cur[2]);
                break;
            }
        }

    }
    public static void union(int a, int b){
        int rA = find(a);
        int rB = find(b);

        if(rA > rB) root[rA] = rB;
        else root[rB] = rA;
    }

    public static int find(int a){
        if(a == root[a]) return a;
        return root[a] = find(root[a]);
    }
}
