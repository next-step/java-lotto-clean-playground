package domain.validator;

public class ManualCountValidator {
    private static final int MIN_MANUAL_COUNT = 0;

    public static void validate(int totalTrialCount, int manualCount) {
        if (manualCount > totalTrialCount) {
            throw new IllegalArgumentException("[ERROR] 수기 작성 로또 갯수가 전체 시도 횟수를 넘어서는 안 됩니다.");
        }
        if (manualCount < MIN_MANUAL_COUNT) {
            throw new IllegalArgumentException("[ERROR] 수기 작성 로또 갯수는 0 이상이어야 합니다.");
        }
    }
}