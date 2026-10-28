class Solution {
    public class MonotonicStack {
        Node top;

        public class Node {
            int index;      // 이 높이로 확장할 수 있는 시작 인덱스
            int minHeight;
            Node next;

            public Node(int index, int minHeight, Node next) {
                this.index = index;
                this.minHeight = minHeight;
                this.next = next;
            }
        }

        public void push(int index, int minHeight) {
            top = new Node(index, minHeight, top);
        }

        public int[] pop() {
            if (top == null) return null;

            int[] result = {top.index, top.minHeight};
            top = top.next;
            return result;
        }

        public int[] peek() {
            if (top == null) return null;

            return new int[]{top.index, top.minHeight};
        }

        public boolean isEmpty() {
            return top == null;
        }
    }

    public int largestRectangleArea(int[] heights) {
        MonotonicStack stack = new MonotonicStack();
        int max = 0;

        // 마지막에 높이 0을 하나 더 처리해 남은 막대를 계산한다.
        for (int i = 0; i <= heights.length; i++) {
            int height = (i == heights.length) ? 0 : heights[i];
            int start = i;

            while (!stack.isEmpty() && stack.peek()[1] > height) {
                int[] popped = stack.pop();

                int width = i - popped[0];
                int area = popped[1] * width;
                max = Math.max(max, area);

                // 현재 높이도 꺼낸 막대의 시작 위치까지 확장 가능하다.
                start = popped[0];
            }

            stack.push(start, height);
        }

        return max;
    }
}