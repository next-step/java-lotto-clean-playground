package domain;

import java.util.ArrayList;
import java.util.List;

public final class WinningNumbers {
    private final Lotto numbers;
    private final int bonusNumber;

    private WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.numbers = new Lotto(toLottoNumbers(numbers));
        validateBonus(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumbers of(List<Integer> numbers, int bonusNumber) {
        return new WinningNumbers(numbers, bonusNumber);
    }

    public int matchCount(LottoTicket ticket) {
        return ticket.lotto().matchCount(numbers);
    }

    public boolean bonusMatched(LottoTicket ticket) {
        return ticket.hasBonus(bonusNumber);
    }

    private void validateBonus(int bonusNumber) {
        LottoNumber bonus = LottoNumber.of(bonusNumber);
        if (numbers.contains(bonus)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> list = new ArrayList<>();
        for (int n : numbers) list.add(LottoNumber.of(n));
        return list;
    }
}
