package domain.lotto;

import domain.common.ExceptionMessage;
import java.util.Objects;

public class ManualCount {

    private final int count;

    public ManualCount(int totalCount, int manualCount) {
        validateNegativeNumber(manualCount);
        validateExceedTotalCount(totalCount, manualCount);
        this.count = manualCount;
    }

    private void validateExceedTotalCount(int totalCount, int manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException(ExceptionMessage.EXCEED_TOTAL_COUNT);
        }
    }

    private void validateNegativeNumber(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(ExceptionMessage.NEGATIVE_NUMBER);
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
