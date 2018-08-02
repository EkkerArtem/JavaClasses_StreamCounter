import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamCounterImplTest {
    private StreamCounter counter;

    @BeforeEach
    void init() {
        counter = new StreamCounterImpl();
    }

    /**
     * Testing the addition in expression.
     */
    @Test
    void calculateAddition() {
        assertEquals(4, counter.calculate("2+2"));

    }

    /**
     * Testing the addition in Subtraction.
     */
    @Test
    void calculateSubtraction() {
        assertEquals(3, counter.calculate("5-2"));
    }

    /**
     * Testing the addition in Multiplication.
     */
    @Test
    void calculateMultiplication() {
        assertEquals(6, counter.calculate("2*3"));
    }

    /**
     * Testing the addition in Division.
     */
    @Test
    void calculateDivision() {
        assertEquals(6, counter.calculate("2*3"));
    }
}