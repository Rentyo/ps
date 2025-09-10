import java.util.*;
import java.io.*;

class Solution
{
    static final int maxValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] arr = new ArrayList[n];
            for(int i = 0; i < n; i++){
                arr[i] = new ArrayList<>();
                for(int j = 0; j < n; j++){
                    int num = Integer.parseInt(st.nextToken());
                    if(num == 0) continue;
                    arr[i].add(j);
                }
            }
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[] v;
            int min = maxValue;
            for(int i = 0; i < n; i++){
                int sum = 0;
                v = new boolean[n];
                queue.offer(new int[]{i, 0});
                int count = 1;
                v[i] = true;
                while(queue.size() > 0){
                    int[] cur = queue.poll();
                    for(int j = 0; j < arr[cur[0]].size(); j++){
                        int next = arr[cur[0]].get(j);
                        if(v[next]) continue;
                        v[next] = true;
                        queue.offer(new int[]{next, cur[1] + 1});
                        count++;
                        sum+= cur[1] + 1;
                    }
                }
                if(count == n) min = Math.min(sum, min);
            }
            sb.append(min).append("\n");
        }
        br.close();
        System.out.println(sb);
    }
}