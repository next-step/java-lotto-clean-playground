package domain.value;

import domain.Lotto;

import java.util.List;

public record BonusNumber(int value) {
    public BonusNumber {
        if (value < 1 || value > Lotto.ALL_LOTTO_NUMBER_SIZE)
            throw new IllegalArgumentException("번호는 1이상 45이하 범위여야 합니다.");
    }

    public void mustNotDuplicate(List<Integer> winning) {
        if (winning.contains(value))
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
