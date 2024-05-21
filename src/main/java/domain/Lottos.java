package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_COUNT_NUMBER = 1000;
    private final int lottoMoney;
    private final List<Lotto> lottos;

    public Lottos(int lottoMoney, CreateLottoNumber createLottoNumber) {
        this.lottoMoney = lottoMoney;
        this.lottos = makeLottos(createLottoNumber);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> makeLottos(CreateLottoNumber createLottoNumber) {
        List<Lotto> lottosBundle = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < lottoMoney / LOTTO_COUNT_NUMBER; i++) {
            Lotto lotto = new Lotto(createLottoNumber);
            sortLottoNumber(lotto.getLottoNumber());
            lottosBundle.add(lotto);
        }
        return lottosBundle;
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
