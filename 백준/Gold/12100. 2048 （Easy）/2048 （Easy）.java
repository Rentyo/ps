import java.io.*;
import java.util.*;
public class Main {
    static int n;
    static int[][][] cmap;
    static int max;
    static int[][] d = {{1, 0} , {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        cmap = new int[6][n][n];
        max = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                cmap[0][i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, cmap[0][i][j]);
            }
        }
        recursive(0);
        System.out.print(max);

    }
    public static void recursive(int count){
        if(count == 5){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n ; j++){
                    max = Math.max(cmap[count][i][j], max);
                }
            }
            return;
        }

        for(int i = 0; i < 4; i++){
            simulate(count , i);
            recursive(count + 1);
        }

    }
    public static void simulate(int count, int dir) {
        int[][] temp = new int[n][n];
        boolean[][] merge = new boolean[n][n];

        switch(dir){
            case 0: // 아래
                for(int j = 0; j < n; j++){
                    int idx = n-1; // 값을 채울 위치
                    for(int i = n-1; i >= 0; i--){
                        if(cmap[count][i][j] == 0) continue;
                        int now = cmap[count][i][j];
                        int k = idx;
                        // 합칠 수 있는지 확인
                        if(k < n-1 && temp[k+1][j] == now && !merge[k+1][j]){
                            temp[k+1][j] *= 2;
                            merge[k+1][j] = true;
                        } else {
                            temp[idx][j] = now;
                            idx--;
                        }
                    }
                }
                break;

            case 1: // 오른쪽
                for(int i = 0; i < n; i++){
                    int idx = n-1;
                    for(int j = n-1; j >= 0; j--){
                        if(cmap[count][i][j] == 0) continue;
                        int now = cmap[count][i][j];
                        int k = idx;
                        if(k < n-1 && temp[i][k+1] == now && !merge[i][k+1]){
                            temp[i][k+1] *= 2;
                            merge[i][k+1] = true;
                        } else {
                            temp[i][idx] = now;
                            idx--;
                        }
                    }
                }
                break;

            case 2: // 위
                for(int j = 0; j < n; j++){
                    int idx = 0;
                    for(int i = 0; i < n; i++){
                        if(cmap[count][i][j] == 0) continue;
                        int now = cmap[count][i][j];
                        int k = idx;
                        if(k > 0 && temp[k-1][j] == now && !merge[k-1][j]){
                            temp[k-1][j] *= 2;
                            merge[k-1][j] = true;
                        } else {
                            temp[idx][j] = now;
                            idx++;
                        }
                    }
                }
                break;

            case 3: // 왼쪽
                for(int i = 0; i < n; i++){
                    int idx = 0;
                    for(int j = 0; j < n; j++){
                        if(cmap[count][i][j] == 0) continue;
                        int now = cmap[count][i][j];
                        int k = idx;
                        if(k > 0 && temp[i][k-1] == now && !merge[i][k-1]){
                            temp[i][k-1] *= 2;
                            merge[i][k-1] = true;
                        } else {
                            temp[i][idx] = now;
                            idx++;
                        }
                    }
                }
                break;

            default: break;
        }

        cmap[count+1] = temp;
    }
}
