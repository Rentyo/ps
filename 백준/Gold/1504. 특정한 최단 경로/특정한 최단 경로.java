import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]>[] edges = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            edges[start].add(new int[]{end, dist});
            edges[end].add(new int[]{start, dist });
        }
        int[][] minDist = new int[4][N+1];
        for(int i = 0; i < 4; i++){
            Arrays.fill(minDist[i], 123456789);
        }
        st= new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            new Comparator<int[]>(){
                @Override 
                public int compare(int[] o1, int[] o2){
                    return Integer.compare(o1[2], o2[2]);
                }
            }
        );


        int v = 0;
        if(num1 == 1) v |= 1;
        pq.offer(new int[]{1, v, 0});

        while(pq.size() > 0){
            int[] cur = pq.poll();
            int pos = cur[0];
            int cv = cur[1];
            int dist = cur[2];

            if(minDist[cv][pos] <= dist) continue;
            minDist[cv][pos] = dist;
            if(cv == 3 && pos == N){
                System.out.println(dist);
                return;
            }

            for(int i = 0; i < edges[pos].size(); i++){
                int nextPos = edges[pos].get(i)[0];
                int nextDist = dist + edges[pos].get(i)[1];
                int nextCv = cv;
                if(nextPos == num1){
                    nextCv |= 1;
                }else if(nextPos == num2){
                    nextCv |= 2;
                }

                if(minDist[nextCv][nextPos] > nextDist){
                    pq.offer(new int[]{nextPos, nextCv, nextDist});
                } 
            }
        }
        System.out.println(-1);


    }
}
