import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int N =  Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        boolean[] v = new boolean[N+1];
        for(int i = 0; i < N+1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i <= M; i++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start =  Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        int count = 0;
        for(int i = 1; i <= N; i++){
            if(v[i]) continue;
            count++;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            v[i] = true;
            while(queue.size() != 0){
                int cur = queue.poll();
                for(int j = 0; j < graph[cur].size(); j++){
                    int next =  graph[cur].get(j);
                    if(v[next]) continue;
                    v[next] = true;
                    queue.offer(next);
                }
            }
        }
        System.out.println(count);
    }
}
