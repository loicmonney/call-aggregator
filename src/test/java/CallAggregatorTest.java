
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CallAggregatorTest {

    @Test
    void maxForEmptyDatasetShouldReturn0() {
        List<Call> calls = new LinkedList<>();
        assertEquals(0, CallAggregator.max(calls));
    }

    @Test
    void maxForDatasetWithOneElementShouldReturn1() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(0, 1));
        assertEquals(1, CallAggregator.max(calls));
    }

    @Test
    void maxForDatasetWithTwoNotOverlappedElementsShouldReturn1() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(0, 1));
        calls.add(new Call(1, 2));
        assertEquals(1, CallAggregator.max(calls));
    }

    @Test
    void maxForDatasetWithTwoOverlappedElementsShouldReturn2() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(0, 1));
        calls.add(new Call(0, 1));
        assertEquals(2, CallAggregator.max(calls));
    }

    /**
     * Scenario for complex dataset 1:
     *
     *                 3--------------6
     *  0--------------3
     *                                6--------------9
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     *
     *
     * Max over time:
     *
     *  |1111|1111|1111|1111|1111|1111|1111|1111|1111|
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     */
    @Test
    void maxForComplexDataset1ShouldReturn1() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(0, 3));
        calls.add(new Call(3, 6));
        calls.add(new Call(6, 9));
        assertEquals(1, CallAggregator.max(calls));
    }


    /**
     * Scenario for complex dataset 2:
     *
     *       1----2
     *                 3--------------6
     *  0--------------3
     *                                6--------------9
     *                                6---------8
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     *
     *
     * Max over time:
     *
     *       |2222|                   |2222|2222|
     *  11111|    |1111|1111|1111|1111|    |    |1111|
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     */
    @Test
    void maxForComplexDataset2ShouldReturn2() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(1, 2));
        calls.add(new Call(3, 6));
        calls.add(new Call(0, 3));
        calls.add(new Call(6, 9));
        calls.add(new Call(6, 8));
        assertEquals(2, CallAggregator.max(calls));
    }

    /**
     * Scenario for complex dataset 3:
     *
     *       1---------3
     *       1----2
     *                 3--------------6
     *  0--------------3
     *            2------------------------7
     *                                6--------------9
     *                                6---------8
     *                                6---------8
     *                                     7---------9
     *                                     7----8
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     *
     *
     * Max over time:
     *                                     |5555|
     *                                |4444|    |
     *       |3333|3333|              |    |    |
     *       |    |    |2222|2222|2222|    |    |2222|
     *  |1111|    |    |    |    |    |    |    |    |
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     */
    @Test
    void maxForComplexDataset3ShouldReturn5() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(1, 3));
        calls.add(new Call(1, 2));
        calls.add(new Call(3, 6));
        calls.add(new Call(0, 3));
        calls.add(new Call(2, 7));
        calls.add(new Call(6, 9));
        calls.add(new Call(6, 8));
        calls.add(new Call(6, 8));
        calls.add(new Call(7, 9));
        calls.add(new Call(7, 8));
        assertEquals(5, CallAggregator.max(calls));
    }

    /**
     * Scenario for complex dataset 4:
     *
     *  0----1
     *            2----3
     *                 3----4
     *                           5---------7
     *                                     7----8
     *  |----|----|----|----|----|----|----|----|----|
     *  0    1    2    3    4    5    6    7    8    9
     *
     *
     * Max over time:
     *
     *  |1111|    |1111|1111|    |1111|1111|1111|
     *  |----|0000|----|----|0000|----|----|----|0000|
     *  0    1    2    3    4    5    6    7    8    9
     */
    @Test
    void maxForComplexDataset4ShouldReturn1() {
        List<Call> calls = new LinkedList<>();
        calls.add(new Call(0, 1));
        calls.add(new Call(2, 3));
        calls.add(new Call(3, 4));
        calls.add(new Call(5, 7));
        calls.add(new Call(7, 8));
        assertEquals(1, CallAggregator.max(calls));
    }
}