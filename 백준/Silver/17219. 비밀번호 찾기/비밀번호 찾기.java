import java.io.*;
import java.util.*;
public class Main {
    public static class Site implements Comparable<Site>{
        String url;
        String pw;

        public  Site(String url, String pw) {
            this.url = url;
            this.pw = pw;
        }
        @Override
        public int compareTo(Site s) {
            return this.url.compareTo(s.url);
        }
    }
    static Site[] sites;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        sites = new Site[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            sites[i] = new Site(st.nextToken(), st.nextToken());
        }
        Arrays.sort(sites);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            sb.append(find(br.readLine())).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static String find(String s){
        int start = 0;
        int end = sites.length -1;

        while(start <= end) {
            int mid = start + (end - start)/2;

            if(sites[mid].url.equals(s)){
                return sites[mid].pw;
            }else if(sites[mid].url.compareTo(s) < 0){
                start = mid + 1;
            }else{
                end = mid -1;
            }
        }
        return "";
    }
}
