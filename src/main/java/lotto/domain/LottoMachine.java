package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.view.OutputView;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    public void buyLotto(int money) {
        List<List<Integer>> logs = new ArrayList<>();
        for (int i = 0; i < money; i += LOTTO_PRICE) {
            Lotto lotto = Lotto.create(new LottoNumberGenerator());
            logs.add(lotto.getNumbers());
        }
        OutputView.printLotto(logs);
    }
}
