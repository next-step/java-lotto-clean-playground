package domain.lotto;

import java.util.Objects;

public class ManualCount {

    private static final String NEGATIVE_NUMBER = "음수 입력을 불가능합니다.";
    private static final String EXCEED_TOTAL_COUNT = "총 구매 개수를 넘을 수 없습니다.";

    private final int count;

    public ManualCount(int totalCount, int manualCount) {
        validateNegativeNumber(manualCount);
        validateExceedTotalCount(totalCount, manualCount);
        this.count = manualCount;
    }

    private void validateExceedTotalCount(int totalCount, int manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException(EXCEED_TOTAL_COUNT);
        }
    }

    private void validateNegativeNumber(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ManualCount that = (ManualCount) object;
        return getCount() == that.getCount();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getCount());
    }
}
