class Solution {
    public class MonotonicStack{
        Node top;
        int size;
        public MonotonicStack(){
            this.size = 0;
        }

        public class Node{
            int index;
            int value;
            Node next;

            public Node(int index, int value, Node next){
                this.index = index;
                this.value = value;
                this.next = next;
            }
        }

        public int[] peek(){
            if(top == null){
                return null;
            } 
            return new int[]{top.index, top.value};
        }

        public void push(int index, int value){
            Node node = new Node(index, value, this.top);
            this.top = node;
            this.size++;
        }

        public int[] pop(){
            if(this.top == null) return null;
            int[] temp = new int[]{this.top.index, this.top.value};
            this.top = this.top.next;
            this.size--;
            return temp;
        }

        public int size(){
            return this.size;
        }

    }

    public int[] dailyTemperatures(int[] temperatures) {
        MonotonicStack st = new MonotonicStack();
        int[] result = new int[temperatures.length];
        result[result.length-1] = 0;
        st.push(result.length-1 , temperatures[result.length-1]);

        for(int i = result.length-1; i >= 0; i--){
            while(st.size() > 0 && st.peek()[1] <= temperatures[i]){
                st.pop();
            }
            if(st.size() > 0){
                result[i] = st.peek()[0] - i;
            }
            st.push(i, temperatures[i]);
        }
        return result;

    }
}