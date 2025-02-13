package domain;

import java.util.List;

public class WinLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    private WinLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinLotto of(List<Integer> lottoNumbers, int bonusNumberInt){
        LottoNumber bonusNumber = new LottoNumber(bonusNumberInt);
        validateDuplicateBonusNumber(lottoNumbers, bonusNumber);

        return new WinLotto(new Lotto(lottoNumbers), bonusNumber);
    }

    private static void validateDuplicateBonusNumber(List<Integer> LottoNumbers, LottoNumber bonusLottoNumber) {
        if(LottoNumbers.contains(bonusLottoNumber.getNumber())){
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
