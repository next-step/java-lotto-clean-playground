package domain;

import java.util.List;

public class WinDiscriminator {

    private Prize prize = new Prize();

    // 로또 하나의 당첨 여부를 확인한다.
    public void discriminateAll(List<Integer> winNumbers, List<Lotto> lottos, int bonus) {
        for (Lotto lotto : lottos) {
            discriminate(winNumbers, lotto, bonus);
        }
    }

    private void discriminate(List<Integer> winNumbers, Lotto lotto, int bonus) {

        List<Integer> lottoNumbers = lotto.getLottoNumber();
        int correctQuantity = 0;

        // 응모한 번호(lottoNumber)가 당첨 번호(winNumbers)에 포함되어 있는지 확인한다.
        for (Integer lottoNumber : lottoNumbers) {
            correctQuantity += compareNumber(winNumbers, lottoNumber);
        }

        // 확인 후 보너스 번호에 대해서 먼저 확인한다.
        // 로직이 매끄럽지는 못하다.
        if (correctQuantity == 5) {
            correctQuantity = checkBonus(lottoNumbers, bonus, correctQuantity);
        }

        checkPrize(correctQuantity);
    }

    private int compareNumber(List<Integer> winNumbers, Integer lottoNumber) {
        if (winNumbers.contains(lottoNumber)) {
            return 1;
        }
        return 0;
    }

    //보너스 볼을 확인해서 보너스 볼을 포함한다면 특별 값을 반환, 그렇지 않다면 원래 값을 반환한다.
    private int checkBonus(List<Integer> lottoNumbers, int bonus, int correctQuantity) {
        if (lottoNumbers.contains(bonus)) {
            return 77;
        }
        return correctQuantity;
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
        if (correctQuantity == 77) {
            prize.addBonusPrizeQuantity();
        }
        if (correctQuantity == 6) {
            prize.addFirstPrizeQuantity();
        }

    }

    public Prize getPrize() {
        return prize;
    }
}

