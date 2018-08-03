public interface Operation extends Comparable<Operation> {
    /**
     * @param number1 first input number for arithmetical operation.
     * @param number2 second input number for arithmetical operation.
     * @return the result of operation.
     */
    int performOperation(Integer number1, Integer number2);

    /**
     * @return the priority of operation.
     */
    int getPriority();
}
