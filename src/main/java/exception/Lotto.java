package exception;

import java.util.ArrayList;

public record Lotto(ArrayList<Integer> lotto) {

    public Lotto {
        validateLottoRange(lotto);
        validateDuplication(lotto);
        validateLottoLength(lotto);
    }

    private void validateLottoRange(ArrayList<Integer> lotto) {
        if (lotto.stream().allMatch(number -> number < 1 || number > 45)) {
            throw new IllegalArgumentException("1 ~ 45사이의 값만 입력할 수 있습니다.");
        }
    }

    private static void validateDuplication(ArrayList<Integer> winningNumbers) {
        if (winningNumbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("로또 번호 중복 입력은 불가능합니다.");
        }
    }

    private void validateLottoLength(ArrayList<Integer> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException("길이가 맞지 않습니다.");
        }
    }

}
