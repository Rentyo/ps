import java.io.*;
import java.util.*;
public class Main {
    public static int[][] d = {
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1}
    };
    // 현재 양분
    static int[][] value;
    // 죽은 나무
    static int[][] dead;
    // 추가 양분
    static int[][] A;
    static int n;
    static PriorityQueue<Integer>[][] pq;

    //현재까지의 나무 개수
    static int sum;
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n  = Integer.parseInt(st.nextToken());
        int m  = Integer.parseInt(st.nextToken());
        int k   = Integer.parseInt(st.nextToken());
        value = new int[n][n];
        dead = new int[n][n];
        A = new int[n][n];
        pq = new PriorityQueue[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                value[i][j] = 5;
                pq[i][j] = new PriorityQueue<>();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            pq[row][col].offer(age);
        }
        sum = 0;
        for(int i = 1; i <= k; i++){
            Spring();
            Summer();
            Fall();
            if(sum == 0) break;
            Winter();
        }
        System.out.println(sum);
    }
    public static void Spring(){
        Queue<int[]> temp = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                while(!pq[i][j].isEmpty()){
                    int age = pq[i][j].poll();
                    if(age <= value[i][j]){
                        value[i][j] -= age;
                        temp.offer(new int[]{i, j, age+1});
                    }else{
                        dead[i][j] += (age/2);
                    }
                }
                while(temp.size() > 0){
                    int[] tree = temp.poll();
                    pq[tree[0]][tree[1]].offer(tree[2]);
                }
            }
        }
    }
    public static void Summer(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                value[i][j] += dead[i][j];
                dead[i][j] = 0;
            }
        }
    }
    public static void Fall(){
        Queue<int[]> temp = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                while(pq[i][j].size() > 0) {
                    int age = pq[i][j].poll();
                    temp.offer(new int[]{i, j, age});
                    if(age % 5 != 0){
                        continue;
                    }
                    for (int k = 0; k < 8; k++) {
                        int nI = i + d[k][0];
                        int nJ = j + d[k][1];
                        if (nI < 0 || nI >= n || nJ < 0 || nJ >= n) continue;
                        temp.offer(new int[]{nI, nJ, 1});
                    }
                }
            }
        }
        sum = temp.size();
        while(temp.size() > 0){
            int[] now = temp.poll();
            pq[now[0]][now[1]].offer(now[2]);
        }
    }
    public static void Winter(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                value[i][j] += A[i][j];
            }
        }
    }
}
