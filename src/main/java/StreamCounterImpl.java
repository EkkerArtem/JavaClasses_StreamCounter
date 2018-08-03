import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StreamCounterImpl implements StreamCounter {

    /**
     * Stack for storing numbers.
     */
    Stack<Integer> numbersStack = new Stack<>();
    /**
     * Stack for storing operations.
     */
    Stack<Operation> operatorsStack = new Stack<>();

    /**
     * Method for processing arithmetical operations.
     * It takes 2 numbers from {@link #operatorsStack}
     * and do the callculation
     */
    private void doOperation() {
        while (!operatorsStack.empty()) {
            Operation stackOperation = operatorsStack.peek();
            Integer number2 = numbersStack.pop();
            Integer number1 = numbersStack.pop();

            int result = stackOperation.performOperation(number1, number2);

            numbersStack.push(result);

            operatorsStack.pop();
        }
    }

    /**
     * Method for comparing operations priorities
     *
     * @param operator storing the operation or preforming it depending on the priority
     */
    private void getPriority(String operator) {
        Operation currentOperation = operationFactory(operator);
        if (!operatorsStack.empty()) {
            Operation stackOperation = operatorsStack.peek();
            if (stackOperation.compareTo(currentOperation) == 0) {
                doOperation();
                operatorsStack.push(currentOperation);
            } else if (stackOperation.compareTo(currentOperation) > 0) {
                operatorsStack.push(currentOperation);
            } else if (stackOperation.compareTo(currentOperation) < 0) {
                doOperation();
                operatorsStack.push(currentOperation);
            }
        } else {
            operatorsStack.push(currentOperation);
        }
    }

    /**
     * Method for arithmetical calculations.
     *
     * @param inputExpression stores input expression.
     * @return final result of calculatin.
     */
    @Override
    public int calculate(String inputExpression) {

        Pattern pattern = Pattern.compile("(\\d+)|(\\+|-|\\*|/?)");
        Matcher matcher = pattern.matcher(inputExpression);
        int lastMatchPos = 0;
        while (matcher.find()) {
            String number = matcher.group(1);
            String operator = matcher.group(2);
            if (number != null) {
                numbersStack.push(Integer.parseInt(number));
            } else if (operator != null && !operator.equals("")) {
                getPriority(operator);
            }
            lastMatchPos = matcher.end();
        }
        doOperation();


        int result = numbersStack.pop();
        if (lastMatchPos != inputExpression.length())
            System.out.println("Invalid string!");

        System.out.println("result: " + result);

        return result;
    }


    private static Operation operationFactory(String val) {
        switch (val) {
            case "+": {
                return new Addition();
            }
            case "-": {
                return new Subtraction();
            }
            case "*": {
                return new Multiplication();
            }
            case "/": {
                return new Division();
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    public static void main(String[] args) {
        StreamCounter streamCounter = new StreamCounterImpl();
        streamCounter.calculate("1+8*2-18/4");
    }
}


