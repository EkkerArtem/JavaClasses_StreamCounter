public class Multiplication implements Operation {
    /**
     * Stores the priority of operation.
     */
    private final int priority = 3;

    /**
     * @param number1 first input number for arithmetical operation.
     * @param number2 second input number for arithmetical operation.
     * @return the result of multiplication.
     */
    @Override
    public int performOperation(Integer number1, Integer number2) {
        return number1 * number2;
    }

    /**
     * @return the priority of operation.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * @param operation takes nad stores some arithmetical operation.
     * @return the result of comparing. If priority > operation.getPriority() it returns more then 0,
     * if priority < operation.getPriority() it returns less then 0, If priority = operation.getPriority() it returns 0.
     */
    @Override
    public int compareTo(Operation operation) {
        return Integer.compare(priority, operation.getPriority());
    }
}
