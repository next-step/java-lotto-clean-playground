package model;

import java.util.ArrayList;
import java.util.List;


public class Consumer {

    private List<Lotto> haveLottos = new ArrayList<>();
    private final ConsumerMoney inputMoney;

    public Consumer(final int money) {

        this.inputMoney = ConsumerMoney.from(money);
    }

    public void BuyLottos() {

        int lottoCount = inputMoney.value() / Lotto.getPrice();

        for (int i = 0; i < lottoCount; i++) {

            Lotto lotto = new Lotto(LottoMachine.makeAutoNumber());
            haveLottos.add(lotto);
        }
    }

    public List<Lotto> getHaveLottos() {
        return haveLottos;
    }

    public int getMoney() {
        return inputMoney.value();
    }
}
