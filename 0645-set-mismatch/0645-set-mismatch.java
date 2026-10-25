class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int[] temp = new int[nums.length+1];
        for(int i = 0; i < nums.length; i++){
            temp[nums[i]]++;
        }
        for(int i = 1; i <= nums.length; i++){
            if(temp[i] == 2){
                ans[0]= i;
            }else if(temp[i] == 0){
                ans[1] = i;
            }
        }
        
        return ans;

    }
}