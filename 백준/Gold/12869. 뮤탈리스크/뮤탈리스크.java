import java.io.*;
import java.util.*;
public class Main {
    static final int[][] d2 = {{-9, -3} , {-3, -9}};
    static final int[][] d3 = {{-9, -3, -1}, {-9, -1, -3}, {-3, -9, -1}, {-3, -1, -9} , {-1, -9, -3}, {-1, -3, -9}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int life1 = 0;
        int life2 = 0;
        int life3 = 0;
        switch(n) {
            case 1 :
                life1 = Integer.parseInt(br.readLine());
                System.out.println(life1 % 9 == 0 ? life1 / 9 : life1 / 9 + 1);
                break;
            case 2 :
                st = new StringTokenizer(br.readLine());
                life1 = Integer.parseInt(st.nextToken());
                life2 = Integer.parseInt(st.nextToken());
                boolean[][] visited = new boolean[life1+1][life2+1];
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{life1, life2, 0});
                visited[life1][life2] = true;

                while(queue.size() > 0){
                    int[] cur = queue.poll();

                    for(int i = 0; i < d2.length; i++){
                        int nextI = Math.max(cur[0] + d2[i][0] , 0);
                        int nextJ = Math.max(cur[1] + d2[i][1] , 0);
                        if(nextI == 0 && nextJ == 0) {
                            System.out.println(cur[2] + 1);
                            return;
                        }
                        if(visited[nextI][nextJ]) continue;
                        visited[nextI][nextJ] = true;
                        queue.offer(new int[]{nextI, nextJ, cur[2] + 1 });
                    }

                }
                break;
            case 3 :
                st = new StringTokenizer(br.readLine());
                life1 = Integer.parseInt(st.nextToken());
                life2 = Integer.parseInt(st.nextToken());
                life3 = Integer.parseInt(st.nextToken());
                boolean[][][] visited2 = new boolean[life1+1][life2+1][life3 +1];
                Queue<int[]> queue2 = new ArrayDeque<>();
                queue2.offer(new int[]{life1, life2,life3 ,0});
                visited2[life1][life2][life3] = true;

                while(queue2.size() > 0){
                    int[] cur = queue2.poll();

                    for(int i = 0; i < d3.length; i++){
                        int nextI = Math.max(cur[0] + d3[i][0] , 0);
                        int nextJ = Math.max(cur[1] + d3[i][1] , 0);
                        int nextK = Math.max(cur[2] + d3[i][2] , 0);
                        if(nextI == 0 && nextJ == 0 && nextK == 0) {
                            System.out.println(cur[3] + 1);
                            return;
                        }
                        if(visited2[nextI][nextJ][nextK]) continue;
                        visited2[nextI][nextJ][nextK] = true;
                        queue2.offer(new int[]{nextI, nextJ, nextK ,cur[3] + 1 });
                    }

                }



                break;
            default :
                break;
        }
    }

}
