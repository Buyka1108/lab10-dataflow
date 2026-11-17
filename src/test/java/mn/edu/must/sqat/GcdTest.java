
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GcdTest {

    @Test
    @DisplayName("G1: zero iterations, x line 13 to line 20")
    void testZeroIteration() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2: multiple iterations, loop-carried DU pairs")
    void testMultipleIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }

    @Test
    @DisplayName("G3: initial x zero")
    void testInitialXZero() {
        assertEquals(5, DataFlowTargets.gcd(0, 5));
    }
}
