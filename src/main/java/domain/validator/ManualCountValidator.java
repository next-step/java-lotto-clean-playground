package domain.validator;

public class ManualCountValidator {

    public static void validate(int totalTrialCount, int manualCount) {
        if (manualCount > totalTrialCount) {
            throw new IllegalArgumentException("[ERROR] 수기 작성 로또 갯수가 전체 시도 횟수를 넘어서는 안 됩니다.");
        }
        if (manualCount < 0) {
            throw new IllegalArgumentException("[ERROR] 수기 작성 로또 갯수는 0 이상이어야 합니다.");
        }
    }
}