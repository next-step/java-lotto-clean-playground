package lotto.domain;

import java.util.ArrayList;
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
        List<LottoNumber> matched = new ArrayList<>(target.getLottoNumbers());  // target 번호 복사본
        matched.retainAll(lotto.getLottoNumbers());  // 내 당첨번호랑 안 겹치는 건 삭제됨
        return matched.size();  // 남은 개수 = 일치 개수
    }

    //원래는 매치스코어를 통해서 당첨번호랑 얼마나 겹치는지(몇개맞았는지) 세는 건데
    //이걸 이제 retainAll()로 바꿔서 당첨번호랑 겹치는 번호를 다 뽑아내고 그 size를 세는 방식으로 바꿀 수 있음
    //private int matchScore(LottoNumber number) {
    //    if (lotto.contains(number)) {
    //        return 1;
    //    }
    //    return 0;
//}

    public Rank rank(Lotto target) {
        int matchCount = countMatch(target);
        boolean hasBonus = matchesBonus(target);
        return Rank.of(matchCount, hasBonus);
    }
}
