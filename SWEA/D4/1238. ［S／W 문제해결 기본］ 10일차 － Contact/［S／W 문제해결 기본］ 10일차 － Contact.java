import java.io.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 10; i++){
            sb.append("#").append(i).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] arr = new ArrayList[101];
            int start = Integer.parseInt(st.nextToken());
 
            for(int j = 0; j <= 100; j++){
                arr[j] = new ArrayList<>();
            }
            st= new StringTokenizer(br.readLine());
            for(int j = 0; j < n/2; j++){
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                arr[n1].add(n2);
            }
            boolean[] v = new boolean[101];
            PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] a1, int[] a2){
                    if(a1[1] != a2[1]){
                        return a1[1] - a2[1];
                    }else{
                        return a1[0] - a2[0];
                    }
                }
            });
            pq.offer(new int[]{start, 0});
            v[start] = true;
            int result = -1;
            while(pq.size() > 0){
                int[] now = pq.poll();
                result = now[0];
                for(int j = 0; j < arr[now[0]].size(); j++){
                    if(v[arr[now[0]].get(j)]) continue;
                    pq.offer(new int[]{arr[now[0]].get(j), now[1]+1});
                    v[arr[now[0]].get(j)] = true;
                }
            }
             
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
     
}