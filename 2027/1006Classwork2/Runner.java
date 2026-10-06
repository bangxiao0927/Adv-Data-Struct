// Reads a group of symbols and reports whether every opening symbol has the
// matching closing symbol in the right place.
public class Runner {
    public static void main(String[] args) {
        String[] test = new String[7];
        test[0] = "(abc(*def)";
        test[1] = "[{}]";
        test[2] = "[{]}";
        test[3] = "[{<()>}]";
        test[4] = "{<html[value=4]*(12)>{$x}}";
        test[5] = "[one]<two>{three}(four)";
        test[6] = "car(cdr(a)(b)))";

        // analyze each group and print true or false
        for (int i = 0; i < test.length; i++) {
            System.out.println(isBalanced(test[i]));
        }
    }

    // The opening and closing symbols line up by index: ( goes with ), [ with ],
    // { with } and < with >.
    private static boolean isBalanced(String expression) {
        String opens = "([{<";
        String closes = ")]}>";
        MyStack<Character> stack = new MyStack<Character>();

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            int openIndex = opens.indexOf(symbol);
            int closeIndex = closes.indexOf(symbol);

            if (openIndex >= 0) {
                // an opening symbol waits on the stack until it is closed
                stack.push(symbol);
            } else if (closeIndex >= 0) {
                // a closing symbol with nothing open cannot be balanced
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (opens.indexOf(top) != closeIndex) {
                    return false;
                }
            }
            // any other character is just a letter, digit or symbol, so skip it
        }

        // anything still open at the end was never closed
        return stack.isEmpty();
    }
}
