import java.io.*;
import java.util.*;
public class Solution {


    static boolean[][] origin;
    static int d;
    static int w;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int T = 1; T<= t; T++){
            sb.append("#").append(T).append(" ");
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            origin = new boolean[d][w];

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++){
                    origin[i][j] = Integer.parseInt(st.nextToken()) == 1;
                }

            }
            if(check(new int[d])) sb.append(0).append("\n");
            else{
                for(int i = 1; i <= d; i++){
                    if(simulate(0, 0, i, new int[d])){
                        sb.append(i).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.print(sb);
    }
    public static boolean simulate(int index,int count ,int target, int[] types){
        if(count + d-index< target ) return false;

        if(count == target){
            if(check(types)) return true;
            return false;
        }
        if(index >= d) return false;



        types[index] = 1;
        if(simulate(index +1,count + 1 ,target, types)) return true;
        types[index] = 2;
        if(simulate(index +1,count + 1, target, types)) return true;
        types[index] = 0;
        if(simulate(index + 1,count, target, types)) return true;




        return false;
    }
    public static boolean check(int[] types){
        for(int i = 0; i < w; i++){
            int max = 0;
            int len = 0;
            boolean now = types[0] == 0 ? origin[0][i] : types[0] != 1;
            for(int j = 0; j < d; j++){
                if(now == (types[j] == 0 ? origin[j][i] : types[j] != 1) ){
                    len++;
                }else{
                    now = !now;
                    max = Math.max(max, len);
                    len = 1;
                }
            }
            max = Math.max(max, len);
            if(max < k) return false;
        }
        return true;
    }
}
