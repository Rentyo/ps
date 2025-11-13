import java.io.*;
import java.util.*;
public class Main {
    static HashMap<Character, int[]> map;
    static char[][] maze;
    static boolean[][] poss;
    static boolean[][] v;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        map.put('U', new int[]{-1, 0});
        map.put('R', new int[]{0, 1});
        map.put('D', new int[]{1, 0});
        map.put('L', new int[]{0, -1});
        maze = new char[N][M];
        poss = new boolean[N][M];
        v = new boolean[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                maze[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(poss[i][j]) {
                    continue;
                }
                poss[i][j] = dfs(i, j);
            }
        }
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(poss[i][j]) count++;
            }
        }
        System.out.println(count);
    }
    public static boolean dfs(int row, int col) {
        // 이미 탈출 여부가 결정된 칸이면 바로 반환
        if (poss[row][col] || v[row][col]) return poss[row][col];

        v[row][col] = true;

        int nr = row + map.get(maze[row][col])[0];
        int nc = col + map.get(maze[row][col])[1];

        boolean res;
        if (nr < 0 || nr >= N || nc < 0 || nc >= M) res = true;
        else res = dfs(nr, nc);

        v[row][col] = false; // 백트래킹 시 해제
        return poss[row][col] = res;
    }
}
