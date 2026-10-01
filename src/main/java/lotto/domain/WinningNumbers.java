package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto lotto;

    public WinningNumbers(List<Integer> numbers) {
        this.lotto = new Lotto(numbers);
    }

    public boolean contains(LottoNumber number) {
        return lotto.getLottoNumbers().contains(number);
        // 당첨 번호에 해당 숫자가 포함되어 있는지 확인
    }

}
