import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int T0=0;T0<T;T0++){
            String s = br.readLine();
            HashSet<Character> set = new HashSet<>();
            for(int i=0;i<s.length();i++){
                set.add(s.charAt(i));
            }
            System.out.println(set.size());
        }
    }
}
