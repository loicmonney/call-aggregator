
public class Call {

    private long startTs;
    private long endTs;

    public Call(long startTs, long endTs) {
        assert (startTs >= 0);
        assert (endTs >= 0);
        assert (endTs > startTs);

        this.startTs = startTs;
        this.endTs = endTs;
    }

    public long getStartTs() {
        return startTs;
    }

    public void setStartTs(long startTs) {
        assert (startTs >= 0);

        this.startTs = startTs;
    }

    public long getEndTs() {
        return endTs;
    }

    public void setEndTs(long endTs) {
        assert (endTs >= 0);

        this.endTs = endTs;
    }
}
