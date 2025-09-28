import java.io.*;
import java.util.*;
public class Main {
    static boolean[] v = new boolean[26];
    static String[] words;
    static int k;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        k= Integer.parseInt(st.nextToken());
        words = new String[n];
        for(int i = 0; i < n; i++){
            words[i] = br.readLine();
        }
        comb(0, 0);
        System.out.println(max);
    }
    static void comb(int count, int index){
        if(count == k){
            max = Math.max(max, check());
        }
        if(index == 26) return;
        v[index] = true;
        comb(count+1, index+1);
        v[index] = false;
        if(index == 'a'-'a' || index == 'c' - 'a' || index == 't'-'a' || index == 'i' -'a' || index == 'n' -'a') return;
        comb(count, index+1);
    }
    static int check(){
        int count = 0;
        boolean c;
        for(int i = 0; i < words.length; i++){
            c= true;
             for(int j = 4; j < words[i].length()-3; j++){
                if( !v[words[i].charAt(j) - 'a'] ){
                    c = false;
                    break;
                }
            }
            if(c) count++;
        }
        return count;
    }
}
