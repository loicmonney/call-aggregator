# Call Aggregator

This tool provides one method, `CallAggregator#max(List<Call>)`, that calculates the maximum number of simultaneous calls.

Example:

```java
// Scenario:
// ========
//
//       1---------3
//       1----2
//                 3--------------6
//  0--------------3
//            2------------------------7
//                                6--------------9
//                                6---------8
//                                6---------8
//                                     7---------9
//                                     7----8
//  |----|----|----|----|----|----|----|----|----|
//  0    1    2    3    4    5    6    7    8    9
//
//
// Max over time:
// =============
//                                     |5555|
//                                |4444|    |
//       |3333|3333|              |    |    |
//       |    |    |2222|2222|2222|    |    |2222|
//  |1111|    |    |    |    |    |    |    |    |
//  |----|----|----|----|----|----|----|----|----|
//  0    1    2    3    4    5    6    7    8    9
//

// Create the list of calls
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

// Aggregate
int max = CallAggregator.max(calls); // returns 5
```
