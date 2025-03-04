package domain;

public class LottoCount {
    private final int count;

    public LottoCount(int count) {
        validateCount(count);
        this.count = count;
    }

    private void validateCount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("로또 개수는 1개 이상이어야 합니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
