import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int clothes = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            for(int j = 0; j < clothes; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String garbage = st.nextToken();
                String key = st.nextToken();
                if(!map.containsKey(key)){
                    map.put(key,1);
                }else{
                    map.put(key, map.get(key)+1);
                }
            }
            int value = 1;
            for(Integer now : map.values()){
                value *= (now+1);
            }
            sb.append(value-1).append("\n");
        }
        System.out.print(sb);




    }
}
