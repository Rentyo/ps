import java.io.*;
import java.util.*;
public class Main{
    static int[][] map;
    static int countB = 0;
    static int countW = 0;
    public static void main(String[] args) throws IOException{
        int n = read();
        StringBuilder sb = new StringBuilder();
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = read();
            }
        }
        search(0, n-1, 0, n-1);
        sb.append(countW).append("\n").append(countB);
        System.out.print(sb);
    }
    public static void search(int startX, int endX, int startY, int endY){
        
        if(startX == endX && startY == endY){
            if(map[startY][startX] == 0){
                countW++;
            }else{
                countB++;
            }
            return;
        }
        
        if(check(startX, endX, startY, endY)){
            if(map[startY][startX] == 0){
                countW++;
            }else{
                countB++;
            }
            return;
        }else{
            int midX = (startX + endX) / 2;
            int midY = (startY + endY) / 2;
            search(startX, midX, startY, midY);
            search(midX+1, endX, startY, midY);
            search(startX, midX, midY+1, endY);
            search(midX+1, endX, midY+1, endY);
        }
    }
    public static boolean check(int startX, int endX, int startY, int endY){
        int a = map[startY][startX];
        for(int i = startY; i <= endY; i++){
            for(int j = startX; j <= endX; j++){
                if(map[i][j] != a) return false;
            }
        }
        return true;
    }
    public static int read() throws IOException{
        int val;
        int total = 0;
        while((val = System.in.read()) > 32){
            total = total*10 + val -'0';
        }
        return total;
    }
}