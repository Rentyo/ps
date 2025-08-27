import java.util.*;
class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        int[] out = new int[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length; i++){
            map.put(order[i][0] , order[i][1]);
            out[order[i][1]]++;
        }
        ArrayList<Integer>[] paths = new ArrayList[n];
        for(int i = 0; i < n; i++){
            paths[i] = new ArrayList<>();
        }

        for(int i = 0; i < path.length; i++){
            paths[path[i][0]].add(path[i][1]);
            paths[path[i][1]].add(path[i][0]);
        }

        if(out[0] != 0) return false;
        Set<Integer> wait = new HashSet<>();
        int cnt = 0;
        boolean[] v = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        if(map.containsKey(0)) out[map.get(0)]--;
        cnt++;
        v[0] = true;
        while(queue.size() != 0){
            int cur =  queue.poll();
            
            for(int i = 0; i < paths[cur].size(); i++){
                if(v[paths[cur].get(i)]) continue;
                
                if(out[paths[cur].get(i)] != 0){
                    wait.add(paths[cur].get(i));
                }else{
                    v[paths[cur].get(i)]= true;
                    queue.offer(paths[cur].get(i));
                    cnt++;
                    if(map.containsKey(paths[cur].get(i))){
                        out[map.get(paths[cur].get(i))]-=1;
                        if(out[map.get(paths[cur].get(i))] == 0 && wait.contains(map.get(paths[cur].get(i)))){
                            queue.offer(map.get(paths[cur].get(i)));
                            v[map.get(paths[cur].get(i))] = true;
                            cnt++;
                            wait.remove(map.get(paths[cur].get(i)));
                        }
                    }
                }
            }

        }
        return cnt == n;
    }
}