
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CallAggregator {

    private static class CallPart {
        private long ts;
        private boolean isStart;

        public CallPart(long ts, boolean isStart) {
            this.ts = ts;
            this.isStart = isStart;
        }
    }

    private static class CallPartComparator implements Comparator<CallPart> {
        @Override
        public int compare(CallPart o1, CallPart o2) {
            if (o1.ts == o2.ts) {
                return 0;
            }
            return o1.ts > o2.ts ? 1 : -1;
        }
    }

    public static int max(List<Call> calls) {
        assert (calls != null);

        final List<CallPart> startAndEnds = new LinkedList<>();
        calls.forEach(call -> {
            startAndEnds.add(new CallPart(call.getStartTs(), true));
            startAndEnds.add(new CallPart(call.getEndTs(), false));
        });

        startAndEnds.sort(new CallPartComparator());

        int max = 0;
        int currentNumberOfCall = 0;
        long lastTs = -1;
        for (CallPart callPart : startAndEnds) {
            if (lastTs != callPart.ts) {
                max = Math.max(max, currentNumberOfCall);
            }
            if (callPart.isStart) {
                currentNumberOfCall++;
            } else {
                currentNumberOfCall--;
            }
        }
        max = Math.max(max, currentNumberOfCall);

        return max;
    }
}
