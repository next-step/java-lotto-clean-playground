package domain.value;

import java.util.HashSet;
import java.util.List;

public record WinningNumbers(List<Integer> values) {

    public static final int LOTTO_NUMBER_SIZE = 6;
    public static final int ALL_LOTTO_NUMBER_SIZE = 45;

    public WinningNumbers {
        validate(values);
    }

    private void validate(List<Integer> values) {
        if (values.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(values).size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("중복된 당첨 번호가 있습니다.");
        }
        for (int n : values) {
            if (n < 1 || n > ALL_LOTTO_NUMBER_SIZE) {
                throw new IllegalArgumentException("당첨 번호는 1 이상 45 이하 범위여야 합니다.");
            }
        }
    }
}
