
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusTest {

    @Test
    @DisplayName("B1: long streak, break, score = 8")
    void testLongStreakWithBreak() {
        int[] attendance = {1, 1, 1, 1, 1, 0, 1};
        assertEquals(8, DataFlowTargets.bonus(attendance, 6));
    }

    @Test
    @DisplayName("B2: empty attendance, score = 0")
    void testEmptyAttendance() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 1));
    }
}
