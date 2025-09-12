import java.util.*;
import java.io.*;
 
class Solution
{   
    static long totalX;
    static long totalY;
    static int n; 
    static int[][] map;
    static long min;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][2];
            totalX = 0;
            totalY = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());
                totalX += map[i][0];
                map[i][1] = Integer.parseInt(st.nextToken());
                totalY += map[i][1];
            }
            min =  Long.MAX_VALUE;
            comb(0, 0, 0, 0);
            sb.append(min).append("\n");
        }
        System.out.print(sb);
    }
    public static void comb(int count, int index, long sumX, long sumY){
        if(count > n/2 || n - index + count < n/2) return;
        if(count == n/2){
            long restX = totalX - sumX;
            long restY = totalY - sumY;
            long calX = sumX - restX;
            long calY = sumY - restY;
            min = Math.min(calX*calX + calY*calY, min);
            return;
        }

        if(index == n) return;

        comb(count+1, index + 1, sumX + map[index][0], sumY + map[index][1]);
        comb(count, index+1, sumX, sumY);
    }
}