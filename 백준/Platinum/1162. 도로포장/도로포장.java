import java.io.*;
import java.util.*;
public class Main {
    
    static class Travel{
        int to;
        long dist;
        int count;

        public Travel(int to, long dist, int count){
            this.to = to;
            this.dist = dist;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long [][] dist = new long[K+1][N];
        ArrayList<Travel>[] arr = new ArrayList[N];
        for(int i = 0 ; i < N; i++){
            arr[i] = new ArrayList<>();
        }
        Long maxDist = Long.MAX_VALUE;
        for(int i = 0; i <= K; i++){
            Arrays.fill(dist[i], maxDist);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            Long d = Long.parseLong(st.nextToken());
            arr[a].add(new Travel(b, d, 0));
            arr[b].add(new Travel(a, d, 0));
        }
        
        PriorityQueue<Travel> pq = new PriorityQueue<>(new Comparator<Travel>(){
            @Override
            public int compare(Travel o1, Travel o2){
                return Long.compare(o1.dist, o2.dist);
            }
        });

        pq.offer(new Travel(0,0,0));

        while(pq.size() > 0){
            Travel cur = pq.poll();
            int pos = cur.to;
            long d = cur.dist;
            int count = cur.count;
            if(dist[count][pos] <= d) continue;
            dist[count][pos] = d;

            for(int i = 0; i < arr[pos].size(); i++){
                Travel next = arr[pos].get(i);
                if(dist[count][next.to] > d + next.dist){
                    pq.offer(new Travel(next.to, d + next.dist, count));
                }

                if(count + 1 <= K && dist[count+1][next.to] > d){
                    pq.offer(new Travel(next.to, d, count+1));
                }
            }
        }
        long min = maxDist;
        for(int i = 0; i <= K; i++){
            min = Math.min(min, dist[i][N-1]);
        }
        System.out.println(min);
    }
}