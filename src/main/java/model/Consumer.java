package model;

import java.util.ArrayList;

public class Consumer {

    private ArrayList<Lotto> haveLottos = new ArrayList<>();
    private int money;

    public Consumer(int money) {

        this.money = money;
    }

    public void BuyLottos() {

        int lottoCount = money / Lotto.getPrice();

        for (int i = 0; i < lottoCount; i++) {

            Lotto lotto = new Lotto();
            lotto.AutoLottoNumber();
            haveLottos.add(lotto);
        }
    }

    public ArrayList<Lotto> getHaveLottos() {
        return haveLottos;
    }

    public int getMoney() {
        return money;
    }
}
