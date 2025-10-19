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
    static ArrayList<Integer>[][] trees;

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
        trees = new ArrayList[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                value[i][j] = 5;
                trees[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            trees[row][col].add(age);
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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ArrayList<Integer> temp = new ArrayList<>();
                Collections.sort(trees[i][j]);
                for(int k = 0; k < trees[i][j].size(); k++){
                    int age = trees[i][j].get(k);
                    if(age <= value[i][j]){
                        value[i][j] -= age;
                        temp.add(age+1);
                    }else{
                        dead[i][j] += (age/2);
                    }
                }
                trees[i][j] = temp;
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
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < trees[i][j].size(); k++) {
                    int age = trees[i][j].get(k);
                    if(age % 5 != 0){
                        continue;
                    }
                    for (int l = 0; l < 8; l++) {
                        int nI = i + d[l][0];
                        int nJ = j + d[l][1];
                        if (nI < 0 || nI >= n || nJ < 0 || nJ >= n) continue;
                        trees[nI][nJ].add(1);
                    }
                }
            }
        }
        int size = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                size += trees[i][j].size();
            }
        }
        sum = size;

    }
    public static void Winter(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                value[i][j] += A[i][j];
            }
        }
    }
}
