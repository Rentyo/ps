import java.util.*;
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int num = 1;
        for(int j = 0; j < target.length; j++){
            while(num < target[j]){
                ans.add("Push");
                ans.add("Pop");
                num++;
            }
            ans.add("Push");
            num++;
        }
        
        return ans;
    }
}