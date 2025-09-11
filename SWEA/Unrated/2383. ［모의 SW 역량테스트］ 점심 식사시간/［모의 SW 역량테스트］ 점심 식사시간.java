import java.io.*;
import java.util.*;
public class Solution {
    static HashMap<Integer, int[]> Humans;
    static HashMap<Integer, int[]> Stairs;
    static int min;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            min = Integer.MAX_VALUE;
            Humans = new HashMap<>();
            Stairs = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    int num = Integer.parseInt(st.nextToken());
                    if(num == 1){
                        Humans.put(Humans.size(), new int[]{i, j});
                    }else if(num > 1){
                        Stairs.put(Stairs.size(), new int[]{i, j, num});
                    }
                }
            }
            combination(0, new boolean[Humans.size()]);    
            sb.append(min).append("\n");
        }
        System.out.print(sb);
    }
    static void combination(int index, boolean[]v){
        if(index == v.length){
            simulate(v);
            return;
        }
        v[index] = true;
        combination(index+1, v);
        v[index] = false;
        combination(index+1, v);
    }
    static void simulate(boolean[] v){
        int[] stair1 = Stairs.get(0);
        int[] stair2 = Stairs.get(1);
        Queue<Integer> queue1 = new ArrayDeque<>();
        for(int i = 0; i < 3; i++){
            queue1.offer(0);
        }
        Queue<Integer> queue2 = new ArrayDeque<>();
        for(int i = 0; i < 3; i++){
            queue2.offer(0);
        }
        PriorityQueue<Integer> wait_stair1 =new PriorityQueue<>();
        PriorityQueue<Integer> wait_stair2 = new PriorityQueue<>();

        for(int i = 0; i < Humans.size(); i++){
            int[] Human = Humans.get(i);
            if(v[i]){
                wait_stair2.offer( Math.abs(Human[0] - stair2[0]) + Math.abs(Human[1] - stair2[1]));
            }else{
                wait_stair1.offer( Math.abs(Human[0] - stair1[0]) + Math.abs(Human[1] - stair1[1]));
            }
        }
        int time1 = 0;
        while(wait_stair1.size() > 0){
            int now = queue1.poll();
            int human = wait_stair1.poll();

            if(now > human){
                now += stair1[2];
            }else{
                now = (human+1)+stair1[2];
            }
            time1 = now;
            queue1.offer(now);
        }

        int time2 = 0;
        while(wait_stair2.size() > 0){
            int now = queue2.poll();
            int human = wait_stair2.poll();

            if(now > human){
                now += stair2[2];
            }else{
                now = (human+1)+stair2[2];
            }
            time2 = Math.max(now, time1);
            queue2.offer(now);
        }
        min = Math.min(min, Math.max(time1, time2));
    }
}