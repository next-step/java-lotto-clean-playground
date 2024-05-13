package domain;

import java.util.ArrayList;
import java.util.List;

public class WinDiscriminator {

    public void discriminateAll(List<LottoNumber> winNumbers, List<Lotto> lottos, int bonus) {
        for (Lotto lotto : lottos) {
            discriminate(winNumbers, lotto, bonus);
        }
    }

    // 로또 하나의 당첨 여부 확인
    private void discriminate(List<LottoNumber> winNumbers, Lotto lotto, int bonus) {
        List<LottoNumber> applyNumbers = lotto.lottoNumbers();
        int correctQuantity = 0;
        boolean isContainBonus = false;

        // 응모한 번호(lottoNumber)가 당첨 번호(winNumbers)에 포함되어 있는지 확인한다.
        for (LottoNumber lottoNumber : applyNumbers) {
            correctQuantity += compareNumber(winNumbers, lottoNumber.number());
        }

        if (compareNumber(applyNumbers, bonus) == 1) {
            isContainBonus = true;
        }

        checkPrize(correctQuantity, isContainBonus);
    }

    private int compareNumber(List<LottoNumber> winNumbers, int lottoNumber) {
        List<Integer> winNumbersList = winNumbersToList(winNumbers);

        if (winNumbersList.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    private List<Integer> winNumbersToList(List<LottoNumber> winNumbers) {
        List<Integer> winNumbersList = new ArrayList<>();
        for (LottoNumber winNumber : winNumbers) {
            winNumbersList.add(winNumber.number());
        }

        return winNumbersList;
    }

    //보너스 볼을 확인해서 보너스 볼을 포함한다면 특별 값을 반환, 그렇지 않다면 원래 값을 반환한다.

    private void checkPrize(int correctQuantity, boolean isContainBonus) {

        if (correctQuantity == Prize._4TH_PRIZE.getCorrectCount()) {
            Prize._4TH_PRIZE.addQuantity();
        }
        if (correctQuantity == Prize._3TH_PRIZE.getCorrectCount()) {
            Prize._3TH_PRIZE.addQuantity();
        }
        if (correctQuantity == Prize._2ND_PRIZE.getCorrectCount()) {
            checkBonus(isContainBonus);
        }
        if (correctQuantity == Prize._1ST_PRIZE.getCorrectCount()) {
            Prize._1ST_PRIZE.addQuantity();
        }
    }

    private void checkBonus(boolean isContainBonus) {
        if (isContainBonus) {
            Prize.BONUS_PRIZE.addQuantity();
            return;
        }
        Prize._2ND_PRIZE.addQuantity();
    }
}

