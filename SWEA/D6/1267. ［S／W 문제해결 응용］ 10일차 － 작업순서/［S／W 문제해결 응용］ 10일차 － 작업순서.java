import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= 10; test_case++){
            sb.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] map = new ArrayList[v+1];
            for(int i = 0 ; i <= v; i++){
                map[i] = new ArrayList<>();
            }
            int [] arr = new int[v+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < e; i++){
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                map[start].add(end);
                arr[end]++;
            }
            
            Deque<Integer> deque = new ArrayDeque<>();
            for(int i = 1; i <= v; i++){
                if(arr[i] == 0) deque.offer(i);
            }

            while(deque.size() > 0){
                int now = deque.poll();
                sb.append(now).append(" ");

                for(int i = 0; i < map[now].size(); i++){
                    int next = map[now].get(i);
                    if(--arr[next] == 0){
                        deque.offer(next);
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
