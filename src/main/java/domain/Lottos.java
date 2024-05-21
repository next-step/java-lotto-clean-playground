package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private static final int LOTTO_COUNT_NUMBER = 1000;
    private final static CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
    private final int lottoMoney;
    private final List<Lotto> lottos;

    public Lottos(int lottoMoney) {
        this.lottoMoney = lottoMoney;
        this.lottos = makeLottos();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> makeLottos() {
        List<Lotto> lottosList = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < lottoMoney / LOTTO_COUNT_NUMBER; i++) {
            Lotto lotto = new Lotto(createLottoNumber);
            sortLottoNumber(lotto.getLottoNumber());
            lottosList.add(lotto);
        }
        return lottosList;
    }

    private void sortLottoNumber(List<Integer> lottoNumber) {
        Collections.sort(lottoNumber);
    }
}
