package model;


import exception.ConsumerMoney;
import exception.DirectCount;

import java.util.ArrayList;
import java.util.List;


public class Consumer {

    private static ConsumerMoney inputMoney;

    private static DirectCount directCount;

    private List<Lotto> haveLottos;

    public Consumer(final String money, final String directText) {

        haveLottos = new ArrayList<>();
        inputMoney = ConsumerMoney.from(money);
        directCount = DirectCount.from(directText, inputMoney.value());
    }

    public void BuyLottos(List<Lotto> autoLottos, List<Lotto> directLottos) {

        if (!directLottos.isEmpty()) {
            haveLottos.addAll(directLottos);
        }

        if (!autoLottos.isEmpty()) {
            haveLottos.addAll(autoLottos);
        }
    }

    public void analizeAllLottos(List<Integer> collectNumber, int bonusBall) {

        int collectedCount = 0;

        for (Lotto lotto : haveLottos) {

            collectedCount = analizeOneLotto(lotto, collectNumber);
            lotto.updateCollectedCount(collectedCount);

            if (lotto.getLottoNumber().contains(bonusBall)) lotto.updateBonusCorrect();
        }
    }

    private int analizeOneLotto(Lotto lotto, List<Integer> collectNumber) {

        int collectedCount = 0;


        for (int i = 0; i < collectNumber.size(); i++) {
            if (lotto.getLottoNumber().contains(collectNumber.get(i))) collectedCount++;

        }
        return collectedCount;
    }


    public List<Lotto> getHaveLottos() {
        return haveLottos;
    }

    public static int getMoney() {
        return inputMoney.value();
    }

    public static int getDirectCount() {
        return directCount.value();
    }
}
