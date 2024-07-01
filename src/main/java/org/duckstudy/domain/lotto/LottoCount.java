package org.duckstudy.domain.lotto;

import java.util.Objects;

public class LottoCount {

    private final int count;

    public LottoCount(final int count) {
        validateLottoCount(count);
        this.count = count;
    }

    private void validateLottoCount(final int count) {
        if (count < 0) {
            throw new IllegalArgumentException("로또 개수는 0개 이상이어야 합니다.");
        }
    }

    public void validateManualLottoCount(final LottoCount totalLottoCount) {
        if (count > totalLottoCount.getCount()) {
            throw new IllegalArgumentException("수동으로 구매할 로또 수가 전체 로또 수를 초과합니다.\n");
        }
    }

    public LottoCount subtract(final LottoCount lottoCount) {
        return new LottoCount(count - lottoCount.count);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoCount that = (LottoCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
