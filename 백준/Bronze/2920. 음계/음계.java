import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[8];
        for(int i = 0; i < 8; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean asc = true;
        boolean desc = true;
        
        for(int i = 0; i < 8; i++){
            if(arr[i] != i + 1)
                asc = false;
            if(arr[i] != 8 - i)
                desc = false;
        }
        
        if(asc) System.out.println("ascending");
        else if(desc) System.out.println("descending");
        else System.out.println("mixed");
    }
}
