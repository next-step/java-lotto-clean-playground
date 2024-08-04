package domain.lotto;

import java.util.Objects;

public class ManualCount {

    private final int count;

    public ManualCount(int count) {
        this.count = count;
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
