package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto lotto; //당첨번호 6개
    private final LottoNumber bonusNumber; //보너스볼 1개 표현

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        this.bonusNumber = createBonusNumber(bonusNumber);
    }

    private LottoNumber createBonusNumber(int bonusNumber) {
        LottoNumber number = new LottoNumber(bonusNumber); // 1~45 범위 검증 (LottoNumber 생성자가 처리)
        validateBonusNumber(number);
        return number;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
        // 당첨 번호에 해당 숫자가 포함되어 있는지 확인
        // 이 번호가 6개 당첨번호 안에 있어? 라고 물어보는 것과 같음
    }

    public boolean matchesBonus(Lotto target) {
        return target.contains(bonusNumber);
        // 이 로또(target)가 내 보너스 번호를 갖고 있어?를 묻는것
    }


    public int countMatch(Lotto target) {
        int matchCount = 0;
        for (LottoNumber lottoNumber :target.getLottoNumbers()) {
            matchCount += matchScore(lottoNumber);
        }
        return matchCount;
    }

    private int matchScore(LottoNumber number) {
        if (lotto.contains(number)) {
            return 1;
        }
        return 0;
    }

}
