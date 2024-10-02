package domain;

import utils.LottoNumberGenerator;

import java.util.ArrayList;

public class LottoShop {
    private final int inputMoney;
    private final static int LOTTO_PRICE = 1000;
    Lottos lottos;

    public LottoShop(int inputMoney) {
        this.inputMoney = inputMoney;
        this.lottos = new Lottos(new ArrayList<>());
    }

    public int countLottoTickets(int inputMoney) {
        return inputMoney / LOTTO_PRICE;
    }

    public Lottos saveLottos() {
        int tickets = countLottoTickets(inputMoney);

        for (int i = 0; i < tickets; i++) {
            lottos.add(new Lotto(LottoNumberGenerator.pickLottoNumbers()));
        }

        return lottos;
    }

}
