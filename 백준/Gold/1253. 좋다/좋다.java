import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < n; i++){
            int target = nums[i];
            int left = 0;
            int right = n-1;

            while(left < right){
                if(nums[left] + nums[right] == target){
                    if(left == i){
                        left++;
                    }else if(right == i){
                        right--;
                    }else{
                        count++;
                        break;
                    }
                }else if(nums[left] + nums[right] > target){
                    right--;
                }else{
                    left++;
                }
            }
        }
        System.out.println(count);
    }
}