import java.io.*;
import java.util.*;
public class Main {
    static final int maxValue = 2001;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t_c = 1; t_c <= T; t_c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] connect = new ArrayList[N+1];
            for(int i = 0; i <= N; i++){
                connect[i] = new ArrayList<>();
            }
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                connect[a].add(b);
            }
            int min = maxValue;

            for(int k = 1; k <= N; k++){
                boolean find = false;
                boolean[] v = new boolean[N+1];
                Deque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{k, 0});

                while(queue.size() > 0){
                    int[] cur = queue.poll();

                    for(int i = 0; i < connect[cur[0]].size(); i++){
                        int next = connect[cur[0]].get(i);

                        if(next == k){
                            min=Math.min(min, cur[1]+1);
                         find = true;
                            break;
                         }
                        if(v[next]) continue;
                        v[next] = true;
                        queue.offer(new int[]{next, cur[1] +1});
                    }
                    if(find) break;
                }
                
            }

            sb.append("Case ").append(t_c).append(": ").append(min != 2001 ? min : -1).append("\n");
        }
        System.out.println(sb);
    }
    
}