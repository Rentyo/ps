import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pos implements Comparable<Pos>{
        int pos;
        int time;
        public Pos(int pos, int time){
            this.pos = pos;
            this.time = time;
        }
        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.time, o.time);
        }


    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] v = new int[100_001];
        Arrays.fill(v, 100_002);
        PriorityQueue<Pos> q = new PriorityQueue<>();
        q.offer(new Pos(N, 0));

        while(!q.isEmpty()){
            Pos p = q.poll();
            if(p.time >= v[p.pos]) continue;
            v[p.pos] = p.time;


            if(p.pos == M){
                System.out.println(p.time);
                return;
            }
            int next1 = p.pos + 1;
            if(next1 <= v.length-1 && v[next1] > p.time+1){
                q.offer(new Pos(next1, p.time+1));
            }
            int next2 = p.pos - 1;
            if(next2 >= 0 && v[next2] > p.time+1) {
                q.offer(new Pos(next2, p.time+1));
            }
            int next3 = p.pos * 2;
            if(next3 != 0 && next3 <= v.length-1 && v[next3] > p.time){
                q.offer(new Pos(next3, p.time));
            }


        }

    }
}
