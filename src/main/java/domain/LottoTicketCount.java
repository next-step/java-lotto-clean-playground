package domain;

public class LottoTicketCount {

    private final int count;

    public LottoTicketCount(int count) {
        if(count < 0) {
            throw new IllegalArgumentException("로또 티켓 수는 0장 이상이어야 합니다.");
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void validateManualCount(int manualCount) {
        if (manualCount < 0 || manualCount > count) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수는 0 이상이여야 하며, 구매 가능한 티켓 수를 초과할 수 없습니다.");
        }
    }

}
