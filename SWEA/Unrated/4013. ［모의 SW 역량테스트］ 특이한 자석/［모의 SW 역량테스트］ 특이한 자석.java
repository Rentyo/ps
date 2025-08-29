import java.util.*;
import java.io.*;
class Solution
{
    static boolean[][] wheels;
    static boolean[] rotate;
    static int[][] importLocation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T_C = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t_c = 1; t_c <= T_C; t_c++){
            sb.append("#").append(t_c).append(" ");
            int k = Integer.parseInt(br.readLine());
            wheels = new boolean[4][8];
            importLocation = new int[4][3]; // 0번째 인덱스는 0 위치 , 1번째 인덱스는 2 위치, 2번째 인덱스는 6번 위치
            for(int i = 0; i < 4; i++){
                st = new StringTokenizer(br.readLine());
                importLocation[i][0] = 0;
                importLocation[i][1] = 2;
                importLocation[i][2] = 6;
                for(int j = 0; j < 8; j++){
                    wheels[i][j] = Integer.parseInt(st.nextToken()) == 1 ;
                }
            }
            
            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken())-1;
                int dir = Integer.parseInt(st.nextToken());
                rotate = new boolean[4];
                simulation(n, dir);
            }
            int sum = 0;
            for(int i = 0; i < 4; i++){
                sum += wheels[i][importLocation[i][0]] ? (int)Math.pow(2, i) : 0;
            }
            sb.append(sum).append("\n");
        }
        System.out.print(sb);
    }
    public static void simulation(int idx , int dir){
        rotate[idx] = true;

        if(idx-1 >= 0 && !rotate[idx-1] && wheels[idx-1][importLocation[idx-1][1]] != wheels[idx][importLocation[idx][2]]){
            simulation(idx-1, dir*-1);
        }

        if(idx+1 < 4 && !rotate[idx+1] && wheels[idx+1][importLocation[idx+1][2]] != wheels[idx][importLocation[idx][1]]){
            simulation(idx+1, dir*-1);
        }
        rotate(idx, dir);
        
    }
    public static void rotate(int idx, int dir){
        if(dir == 1){
            importLocation[idx][0] = (importLocation[idx][0] + 7) % 8;
            importLocation[idx][1] = (importLocation[idx][1] + 7) % 8;
            importLocation[idx][2] = (importLocation[idx][2] + 7) % 8; 
        }else{
            importLocation[idx][0] = (importLocation[idx][0] + 1) % 8;
            importLocation[idx][1] = (importLocation[idx][1] + 1) % 8;
            importLocation[idx][2] = (importLocation[idx][2] + 1) % 8;
        }
    }
	
}