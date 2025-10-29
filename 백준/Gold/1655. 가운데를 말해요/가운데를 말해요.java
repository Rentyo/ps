import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> high = new PriorityQueue<>();
        PriorityQueue<Integer> low = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return -1 * Integer.compare(o1, o2);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            int now = Integer.parseInt(br.readLine());
            int highInLow = low.size() == 0 ? 0 : low.peek();
            int lowInHigh = high.size() == 0 ? 10001 : high.peek();

            if(i % 2 == 0){
                if(highInLow > now){
                    high.offer(low.poll());
                    low.offer(now);
                }else{
                    high.offer(now);
                }
            }else{
                if(lowInHigh < now){
                    low.offer(high.poll());
                    high.offer(now);
                }else{
                    low.offer(now);
                }
            }
            sb.append(low.peek()).append("\n");
        }
        System.out.println(sb);
        
    }
}