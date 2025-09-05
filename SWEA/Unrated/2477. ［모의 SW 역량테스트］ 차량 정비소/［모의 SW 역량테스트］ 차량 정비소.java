import java.util.*;
import java.io.*;

class Solution
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int T = 1; T<= t; T++){
            sb.append("#").append(T).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int targetA = Integer.parseInt(st.nextToken())-1;
            int targetB = Integer.parseInt(st.nextToken())-1;
            
            int[] a_time = new int[n];
            st = new StringTokenizer(br.readLine());
            PriorityQueue<int[]> aWait_time = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                    else return Integer.compare(o1[0], o2[0]);
                }
            });
            PriorityQueue<int[]> aWait_num = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return Integer.compare(o1[0], o2[0]);
                }
            });
            for(int i = 0; i < n; i++){
                a_time[i] = Integer.parseInt(st.nextToken());
                aWait_time.offer(new int[]{i, 0});
            }
            PriorityQueue<int[]> bWait_time = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    if(o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
                    else return Integer.compare(o1[0], o2[0]);
                }
            });
            PriorityQueue<int[]> bWait_num = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    return Integer.compare(o1[0], o2[0]);
                }
            });
            int[] b_time = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++){
                b_time[i] = Integer.parseInt(st.nextToken());
                bWait_time.offer(new int[]{i, 0});
            }
            
            PriorityQueue<int[]> c_time = new PriorityQueue<>(new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2){
                    if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                    return Integer.compare(o1[1], o2[1]);
                }
            });
            st = new StringTokenizer(br.readLine());

            int sum = 0;
            for(int i = 0; i < k; i++){
                int customer = Integer.parseInt(st.nextToken());
                while(aWait_time.size() > 0){
                    int[] cur = aWait_time.poll();
                    if(cur[1] <= customer){
                        aWait_num.offer(cur);
                    }else{
                        if(aWait_num.size() == 0){
                            aWait_num.offer(cur);
                        }else{
                            aWait_time.offer(cur);
                        }
                        break;
                    }
                }

                int[] cur = aWait_num.poll();
                if(cur[1] <= customer){
                    cur[1] = customer + a_time[cur[0]];
                }else{
                    cur[1] += a_time[cur[0]];
                }
                customer = cur[1];
                aWait_time.offer(cur);
                while(aWait_num.size() > 0){
                    aWait_time.offer(aWait_num.poll());
                }
                if(cur[0] == targetA){
                    c_time.offer(new int[]{cur[0], customer, 1, i});
                }else{
                    c_time.offer(new int[]{cur[0], customer, 0, i});
                }
            }
            
            while(c_time.size() > 0){
                int[] customer = c_time.poll();
                while(bWait_time.size() > 0){
                    int[] cur = bWait_time.poll();
                    if(cur[1] <= customer[1]){
                        bWait_num.offer(cur);
                    }else{
                        if(bWait_num.size() == 0){
                            bWait_num.offer(cur);
                        }else{
                            bWait_time.offer(cur);
                        }
                        break;
                    }
                }

                int[] cur = bWait_num.poll();
                if(cur[1] <= customer[1]){
                    cur[1] = customer[1] + b_time[cur[0]];
                }else{
                    cur[1] += b_time[cur[0]];
                }
                customer[1] = cur[1];
                bWait_time.offer(cur);
                while(bWait_num.size() > 0){
                    bWait_time.offer(bWait_num.poll());
                }
                if(cur[0] == targetB && customer[2] == 1){
                    sum += (customer[3] + 1);
                }                
            }
            sb.append(sum == 0 ? -1 : sum).append("\n");
        }
        System.out.print(sb);
    }
}