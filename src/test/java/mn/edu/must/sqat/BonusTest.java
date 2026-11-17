
package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("B3: all absent, threshold zero, score = 5")
    void testAllAbsentThresholdZero() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{0, 0, 0}, 0));
    }

    @Test
    @DisplayName("B4: exact attendance threshold, score = 5")
    void testExactThreshold() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{1, 0, 1}, 2));
    }

    @Test
    @DisplayName("B5: long streak below threshold, score = 3")
    void testLongStreakBelowThreshold() {
        assertEquals(3,
            DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 6));
    }

    @Test
    @DisplayName("B6: null attendance throws exception")
    void testNullAttendance() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 1));
    }

    @Test
    @DisplayName("B7: negative threshold throws exception")
    void testNegativeThreshold() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(new int[]{1}, -1));
    }

    @Test
    @DisplayName("B8: shorter streak after break, score = 5")
    void testShorterStreakAfterBreak() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{1, 1, 1, 0, 1}, 4));
    }

@Test
@DisplayName("B9: separated attendance detects M2")
void testSeparatedAttendanceNoLongStreak() {
    int[] attendance = {1, 0, 1, 0, 1, 0, 1, 0, 1};
    assertEquals(5, DataFlowTargets.bonus(attendance, 5));
}

}
