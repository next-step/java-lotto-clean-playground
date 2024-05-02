package domain;

import java.util.List;

public class WinDiscriminator {

    private Prize prize = new Prize();

    // 로또 하나의 당첨 여부를 확인한다.
    private void discriminate(List<Integer> winNumbers, Lotto lotto) {

        List<Integer> lottoNumbers = lotto.getLottoNumber();
        int correctQuantity = 0;

        // 응모한 번호가 당첨 번호에 포함되어 있는지 확인한다.
        for (Integer lottoNumber : lottoNumbers) {
            correctQuantity += compareNumber(winNumbers, lottoNumber);
        }

        checkPrize(correctQuantity);
    }

    private int compareNumber(List<Integer> winNumbers, Integer lottoNumber) {
        if (winNumbers.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    private void checkPrize(int correctQuantity) {
        if (correctQuantity == 3) {
            prize.addFourthPrizeQuantity();
        }
        if (correctQuantity == 4) {
            prize.addThirdPrizeQuantity();
        }
        if (correctQuantity == 5) {
            prize.addSecondPrizeQuantity();
        }
        if (correctQuantity == 6) {
            prize.addFirstPrizeQuantity();
        }

    }

    public void discriminateAll(List<Integer> winNumbers, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            discriminate(winNumbers, lotto);
        }
    }

    public Prize getPrize() {
        return prize;
    }
}

