package domain;

import generator.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoSystem {
    private static final int LOTTO_PRICE = 1000;

    private final Integer lottoCount;
    private final Lottos lottos;

    public LottoSystem(Integer inputMoney, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoCount = inputMoney / LOTTO_PRICE;
        this.lottos = new Lottos(createLottos(lottoNumberGenerator));
    }

    private List<Lotto> createLottos(LottoNumberGenerator lottoNumberGenerator) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(lottoNumberGenerator.generate()));
        }
        return lottos;
    }

    public Lottos getLottos() {
        return lottos;
    }

    public PurchasedLottoNumbers getPurchasedLottoNumbers() {
        return lottos.toNumbers();
    }
}
