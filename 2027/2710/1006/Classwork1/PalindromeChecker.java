public class PalindromeChecker {
    public static void main(String[] args) {
        // Test strings from your prompt
        String[] testStrings = {
            "one two three two one",
            "1 2 3 4 5",
            "one two three four five",
            "1 2 3 a b c c b a 3 2 1"
        };

        for (String str : testStrings) {
            boolean isPalindrome = checkPalindromeList(str);
            System.out.println(isPalindrome);
        }
    }

    public static boolean checkPalindromeList(String input) {
        String[] words = input.split(" ");

        MyStack<String> stack = new MyStack<>();
        MyQueue<String> queue = new MyQueue<>();

        // Add each word to both the Queue and the Stack
        for (String word : words) {
            stack.push(word);
            queue.add(word);
        }

        // Pop and Poll each of them, comparing results sequentially
        while (!stack.empty() && !queue.isEmpty()) {
            String fromStack = stack.pop();
            String fromQueue = queue.poll();

            if (!fromStack.equals(fromQueue)) {
                return false; 
            }
        }

        return true;
    }
}
