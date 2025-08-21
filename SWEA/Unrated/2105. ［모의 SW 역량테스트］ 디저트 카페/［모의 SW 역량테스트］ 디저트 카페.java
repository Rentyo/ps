import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
            {1, 1},
            {1, -1},
            {-1, -1},
            {-1, 1}
    };
    static int max;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for(int test_case=1;test_case<=t;test_case++){
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for(int i=0;i<n;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = -1;
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 1; k <= n; k++) {
                        int nPosRow = i + k * d[0][1];
                        int nPosCol = j + k * d[0][1];
                        if (nPosRow >= n || nPosCol >= n) break;
                        for (int l = 1; l <= n; l++) {
                            int nnPosRow = nPosRow + l * d[1][0];
                            int nnPosCol = nPosCol + l * d[1][1];
                            if (nnPosRow >= n || nnPosCol < 0) break;
                            int nnnPosRow = i - l * d[3][0];
                            int nnnPosCol = j - l * d[3][1];
                            if (nnnPosRow >= n || nnnPosCol < 0) break;
                            simulate(i, j, nPosRow, nPosCol, nnPosRow, nnPosCol, nnnPosRow, nnnPosCol);
                        }
                    }
                }
            }

            sb.append(max).append("\n");
        }
        System.out.print(sb);
    }

    private static void simulate(int i, int j, int nPosRow, int nPosCol, int nnPosRow, int nnPosCol, int nnnPosRow, int nnnPosCol) {
        HashSet<Integer> set = new HashSet<>();
        for(int row = 0; row < nPosRow-i; row++){
            if(set.contains(map[i +row* d[0][0]][j + row* d[0][1]])) return;
            set.add(map[i +row* d[0][0]][j + row* d[0][1]]);
            if(set.contains(map[nnPosRow+row*d[2][0]][nnPosCol + row* d[2][1]])) return;
            set.add(map[nnPosRow+row*d[2][0]][nnPosCol + row* d[2][1]]);
        }
        for(int row = 0; row < nnPosRow-nPosRow; row++){
            if(set.contains(map[nPosRow + row*d[1][0]][nPosCol + row*d[1][1]])) return;
            set.add(map[nPosRow + row*d[1][0]][nPosCol + row*d[1][1]]);
            if(set.contains(map[nnnPosRow + row*d[3][0]][nnnPosCol + row*d[3][1]])) return;
            set.add(map[nnnPosRow + row*d[3][0]][nnnPosCol + row*d[3][1]]);
        }

        max = Math.max(max, set.size());
    }
}
