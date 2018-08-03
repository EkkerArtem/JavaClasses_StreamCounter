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
            Integer firtstNumber = numbersStack.pop();
            Integer secondNumber = numbersStack.pop();

            int result = stackOperation.performOperation(secondNumber, firtstNumber);

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
            if (lastMatchPos != matcher.start()) {
                throw new IllegalArgumentException();
            }
            lastMatchPos = matcher.end();
        }
        doOperation();

        return numbersStack.pop();
    }


    /**
     * @param value stores the operation symbol.
     * @return the operation that is needed to do.
     */
    private static Operation operationFactory(String value) {
        switch (value) {
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


