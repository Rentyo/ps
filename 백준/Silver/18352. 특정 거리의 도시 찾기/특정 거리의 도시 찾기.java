import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int X = sc.nextInt();
        int [] times = new int[N+1];
        Arrays.fill(times, 300001);
        times[X] = 0;
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        for(int i = 0 ; i <= N; i++){
            map.add(new ArrayList<>());
        }

        for(int i = 0 ; i < M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            map.get(a).add(b);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(X);
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < map.get(now).size(); i++) {
                int nextNode = map.get(now).get(i);
                if (times[nextNode] == 300001) {
                    times[nextNode] = times[now] + 1;
                    q.offer(nextNode);
                }
            }
        }
        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (times[i] == K) {
                System.out.println(i);
                check = true;
            }
        }
        if (!check) {
            System.out.println(-1);
        }
    }
}