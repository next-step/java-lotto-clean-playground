package domain;

import utils.LottoNumberGenerator;

import java.util.ArrayList;

public class LottoShop {
    private final static int LOTTO_PRICE = 1000;
    private Lottos lottos;

    public LottoShop() {
        this.lottos = new Lottos(new ArrayList<>());
    }

    // 자동 로또 개수
    public int countAutoLottoTickets(int inputMoney, int manualCount) {
        return (inputMoney / LOTTO_PRICE) - manualCount;
    }

    // 자동 로또 생성 및 로또 합치기
    public Lottos saveLottos(int autoLottoCount, Lottos manualLottos) {
        for (Lotto manualLotto : manualLottos.getLottos()) {
            lottos.add(new Lotto(manualLotto.getLotto()));
        }
        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(LottoNumberGenerator.pickLottoNumbers()));
        }

        return lottos;
    }
}
