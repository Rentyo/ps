import java.io.*;
import java.util.*;
public class Solution {
    static int[][] d = {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    static class Cell {
        int life;
        int now;
        public Cell(int life){
            this.life = life;
            this.now = 0;
        }
    }
    static HashMap<Integer, Cell> noActive;
    static HashMap<Integer, Cell> Active;
    static Set<Integer> Dead;

    static int size;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int tc = 1; tc <= T; tc++){
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            size = 1000;
            int inputrow = Integer.parseInt(st.nextToken());
            int inputcol = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            noActive = new HashMap<>();
            Active = new HashMap<>();
            Dead = new HashSet<>();
            for(int i = 500 ; i < 500 + inputrow; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 500; j < 500 + inputcol; j++){
                    int life = Integer.parseInt(st.nextToken());
                    if(life > 0){
                        noActive.put(size*i + j, new Cell(life));
                    }
                }
            }
            simulate(time);
            sb.append(noActive.size() + Active.size()).append("\n");
        }
        System.out.print(sb);
    }
    static void simulate(int time){
        for(int i = 1; i <= time; i++){
            Queue<Integer> delqueue = new ArrayDeque<>();
            for(Map.Entry<Integer, Cell> entry : noActive.entrySet()){
                 if(++entry.getValue().now == entry.getValue().life){
                    entry.getValue().now = -1;
                    Active.put(entry.getKey(), entry.getValue());
                    delqueue.offer(entry.getKey());
                } 
            }
            while(delqueue.size() > 0) noActive.remove(delqueue.poll());
             for(Map.Entry<Integer, Cell> entry : Active.entrySet()){
                if(++entry.getValue().now == entry.getValue().life){
                    Dead.add(entry.getKey());
                    delqueue.offer(entry.getKey());
                }
                
                if(entry.getValue().now == 1){
                    int R = entry.getKey() / size;
                    int C = entry.getKey() % size;

                    for(int j = 0; j < 4; j++){
                        int nR = R + d[j][0];
                        int nC = C + d[j][1];
                        int key = nR * size + nC;
                        if(nR < 0 || nC < 0 || nR >= size || nC >= size) continue;
                        if(Active.containsKey(key) || Dead.contains(key)) continue;
                        if(noActive.containsKey(key)){
                            if(noActive.get(key).now == 0 && noActive.get(key).life < entry.getValue().life){
                                noActive.get(key).life = entry.getValue().life;
                            }
                        }else{
                            noActive.put(key, new Cell(entry.getValue().life));
                        }
                    }
                }
            }
            while(delqueue.size() > 0) Active.remove(delqueue.poll());
        }
    }
}