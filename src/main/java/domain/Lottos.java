package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_COUNT_NUMBER = 1000;

    private final List<Lotto> lottos;

    public Lottos(CreateLottoNumber createLottoNumber, int lottoMoney) {
        this.lottos = makeLottos(createLottoNumber, lottoMoney);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> makeLottos(CreateLottoNumber createLottoNumber, int lottoMoney) {
        List<Lotto> lottosBundle = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < lottoMoney / LOTTO_COUNT_NUMBER; i++) {
            Lotto lotto = new Lotto(createLottoNumber);
            lottosBundle.add(lotto);
        }
        return lottosBundle;
    }
}
