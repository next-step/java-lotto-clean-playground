package domain;

public class ManualLottoCount {
    private final int count;

    public ManualLottoCount(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("유효하지 않은 구매 개수입니다.");
        }
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
