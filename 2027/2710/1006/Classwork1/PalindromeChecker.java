public class PalindromeChecker {
    public static void main(String[] args) {
        // Test cases provided in the prompt
        String[] testStrings = {
            "one two three two one",
            "1 2 3 4 5",
            "one two three four five",
            "1 2 3 a b c c b a 3 2 1"
        };

        for (String str : testStrings) {
            boolean isPalindrome = checkPalindromeList(str);
            System.out.println('"' + str + "\" -> " + isPalindrome + " (it is " + (isPalindrome ? "" : "not ") + "a palinlist)");
        }
    }

    public static boolean checkPalindromeList(String input) {
        // Split the string into an array of words by space
        String[] words = input.split(" ");

        MyStack<String> stack = new MyStack<>();
        MyQueue<String> queue = new MyQueue<>();

        // Add each word to both data structures
        for (String word : words) {
            stack.push(word);
            queue.add(word);
        }

        // Compare elements sequentially
        // Since both have the same length, we can loop through the size
        while (!stack.empty() && !queue.isEmpty()) {
            String fromStack = stack.pop();
            String fromQueue = queue.poll();

            // If any word mismatch occurs, it's not a palindrome list
            if (!fromStack.equals(fromQueue)) {
                return false;
            }
        }

        return true;
    }
}
