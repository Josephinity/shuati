package Stack; /**
 150. Evaluate Reverse Polish Notation

 Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 Some examples:
 ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */

import java.util.Stack;
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < tokens.length; i++) {

            if(!Character.isDigit(tokens[i].charAt(tokens[i].length() - 1))) {

                int num1 = stack.pop(), num2 = stack.pop();

                stack.push(result(num2, num1, tokens[i].charAt(0))); //to avoid division by 0, give num2 as the first param

            } else {

                stack.push(Integer.parseInt(tokens[i]));

            }

        }

        return stack.isEmpty()? 0: stack.pop();

    }

    private int result(int a, int b, char op) {

        switch(op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            default:  return a / b;
        }

    }
}
