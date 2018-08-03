import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamCounterImplTest {
    private StreamCounterImpl counter;


    @BeforeEach
    void init() {
        counter = new StreamCounterImpl();
    }

    /**
     * Testing the arithmetical operations together.
     */
    @Test
    void calculateAll() {
        assertEquals(13, counter.calculate("1+8*2-18/4"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void calculateAddition() {
        assertEquals(16, counter.calculate("5+11"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void calculateSubstraction() {
        assertEquals(2, counter.calculate("5-3"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void calculateMultiplication() {
        assertEquals(9, counter.calculate("3*3"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void calculateDivion() {
        assertEquals(4, counter.calculate("16/4"));

    }

    /**
     * Testing the illegal arguments.
     */
    @Test
    void calculateInvalidData() {
        assertThrows(IllegalArgumentException.class, ()->counter.calculate("1+dasd8*dasd2-18fewfewf/4fwefwefew"));

    }

}