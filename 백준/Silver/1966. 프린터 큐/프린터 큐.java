import java.io.*;
import java.util.*;
public class Main {
    static class Num{
        int num;
        int index;

        public Num(int num, int index){
            this.num = num;
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t_c= 1; t_c <= T; t_c++){
            st = new StringTokenizer(br.readLine());
            Queue<Num> queue = new ArrayDeque<>();
            int count = Integer.parseInt(st.nextToken());
            int nums[] =  new int[count];
            int find = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < count; j++){
                nums[j] = Integer.parseInt(st.nextToken());
                queue.offer(new Num(nums[j] , j));
            }
            Arrays.sort(nums);
            int seq = 1;
            int index = count-1;
            while(!queue.isEmpty()){
                Num now = queue.poll();

                if(now.num != nums[index]) {          // 아직 최고 우선순위 아님 → 뒤로 보냄
                    queue.offer(now);
                } else {                              // 출력됨
                    if(now.index == find) {           // 출력되는 순간에 find면 정답
                        sb.append(seq).append("\n");
                        break;
                    }
                    seq++;
                    index--;
                }
            }
        }
        System.out.println(sb);
    }
}