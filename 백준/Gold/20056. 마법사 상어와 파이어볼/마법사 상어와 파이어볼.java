
import java.util.*;
import java.io.*;
public class Main {
    static final int[][] d = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };
    static class FireBall{
        int r;
        int c;
        int m;
        int s;
        int d;
        FireBall(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N;
    static int K;
    //격자판
    static HashSet<Integer>[][] map;
    //id 값과 FireBall
    static HashMap<Integer, FireBall> fireBalls;
    // 만약 FireBall이 합쳐졌을 때 기존 FireBall이 없어지면서 남긴 id 재사용
    static Queue<Integer> ids;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new HashSet[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = new HashSet<>();
            }
        }
        fireBalls  = new HashMap<>();
        ids = new ArrayDeque<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int row =  Integer.parseInt(st.nextToken())-1;
            int col =  Integer.parseInt(st.nextToken())-1;
            int m =   Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d =   Integer.parseInt(st.nextToken());
            FireBall fb = new FireBall(row,col,m,s,d);
            fireBalls.put(i, fb);
            map[row][col].add(i);
        }


        for(int i = 0; i < K; i++){
            simulate();
        }

        int sum = 0;
        for(Map.Entry<Integer, FireBall> entry : fireBalls.entrySet()){
            sum +=  entry.getValue().m;
        }
        System.out.println(sum);
    }
    public static void simulate(){
        // FireBall이 전부 이동한 후의 FireBall들의 위치
        HashSet<String> pos = new HashSet<>();
        for(Map.Entry<Integer, FireBall> entry    :fireBalls.entrySet()){
            FireBall fb = entry.getValue();
            map[fb.r][fb.c].remove(entry.getKey());
            int nR = (fb.r + d[fb.d][0] * (fb.s % N) + N) % N;
            int nC = (fb.c + d[fb.d][1] * (fb.s % N) + N) % N;

            map[nR][nC].add(entry.getKey());
            pos.add(nR + " " + nC);

            fb.r = nR;
            fb.c = nC;
        }
        for(String str : pos){
            int r = Integer.parseInt(str.split(" ")[0]);
            int c = Integer.parseInt(str.split(" ")[1]);

            if(map[r][c].size() >= 2){
                int sumM = 0;
                int size = map[r][c].size();
                int sumS = 0;
                int odd = 0;
                int even = 0;
                for(Integer i : map[r][c]){
                    ids.add(i);
                    sumM += fireBalls.get(i).m;
                    sumS += fireBalls.get(i).s;
                    if(fireBalls.get(i).d % 2 == 0) even++;
                    else odd++;
                    fireBalls.remove(i);
                }
                map[r][c].clear();
                if(sumM < 5) continue;

                int startD = (odd == 0 || even == 0) ? 0 : 1;
                for(int i = 0; i < 4; i++){
                    if(ids.size() > 0){
                        fireBalls.put(ids.poll(), new FireBall(r, c, sumM/5, sumS/size, startD));
                    }else{
                        fireBalls.put(fireBalls.size(), new FireBall(r, c, sumM/5, sumS/size, startD));
                    }
                    startD += 2;
                }
            }
        }
    }
}
