import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int max = 0;
    static int count;
    static List<List<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList.get(b).add(a); // b를 해킹하면 a도 해킹 가능
        }

        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            count = 0;
            dfs(i);
            result[i] = count;
            max = Math.max(max, count); // 올바르게 max 갱신
        }

        for (int i = 1; i <= n; i++) {
            if (result[i] == max) sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    public static void dfs(int node) {
        visited[node] = true;
        for (int next : adjList.get(node)) {
            if (!visited[next]) {
                count++;
                dfs(next);
            }
        }
    }
}