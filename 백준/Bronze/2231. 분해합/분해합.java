import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        int n = Integer.parseInt(st);
        int temp = 0;
        int val =0;
        boolean check = false;
        for(int i = Math.max(1, n- (st.length() * 9)); i <= n ; i++){
            temp = i;
            val = i;
            while(temp != 0){
                val += temp%10;
                temp /= 10;
            }
            if(val == n){
                System.out.print(i);
                check = true;
                break;
            }
        }
        if(!check)System.out.print(0);
        
    }

}