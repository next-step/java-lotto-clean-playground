package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private final Set<LottoNumber> numbers;
    private LottoNumber bonusBall;

    public WinningNumbers(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        this.numbers = new HashSet<>(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != Lotto.LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 반드시 " + Lotto.LOTTO_TICKET_SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    public void setBonusBall(LottoNumber bonusBall) {
        validateBonusBallDuplication(bonusBall);
        this.bonusBall = bonusBall;
    }

    public LottoNumber getBonusBall() {
        if (bonusBall == null) {
            throw new IllegalArgumentException("보너스 볼이 설정되지 않았습니다.");
        }
        return bonusBall;
    }

    private void validateBonusBallDuplication(LottoNumber bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }
}
