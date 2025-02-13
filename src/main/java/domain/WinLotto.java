package domain;

import java.util.List;

public class WinLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinLotto of(Lotto lotto, LottoNumber bonusNumber){
        validateDuplicateBonusNumber(lotto, bonusNumber);

        return new WinLotto(lotto, bonusNumber);
    }

    private static void validateDuplicateBonusNumber(Lotto lotto, LottoNumber bonusLottoNumber) {
        if(lotto.getNumbers().contains(bonusLottoNumber.getNumber())){
            throw new IllegalArgumentException("지난주 당첨 번호와 다른 번호를 입력해주세요.");
        }
    }

    public LottoRank calculateRank(Lotto comparedLotto){
        return LottoRank.of(getMatchedCount(comparedLotto), isMatchedBonusNumber(comparedLotto));
    }

    private int getMatchedCount(Lotto comparedLotto) {
        List<Integer> comparedLottoNumbers = comparedLotto.getNumbers();

        return lotto.getNumbers()
                .stream()
                .filter(comparedLottoNumbers::contains)
                .toList()
                .size();
    }

    private boolean isMatchedBonusNumber(Lotto comparedLotto) {
        return comparedLotto.getNumbers().contains(bonusNumber.getNumber());
    }
}
