package lotto.domain;

public class LottoCount {
    private final int count;

    public LottoCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("로또 구매 개수는 0 이상이어야 합니다.");
        }
    }

    public LottoCount subtract(LottoCount other) {
        if (count < other.count) {
            throw new IllegalArgumentException("차감할 개수는 현재 개수를 초과할 수 없다.");
        }

        return new LottoCount(count - other.count);
    }

    public int getCount() {
        return count;
    }
}
