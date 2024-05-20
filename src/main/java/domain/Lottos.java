package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private static final int INITIAL_NUMBER = 0;
    private final CreateLottoNumber createLottoNumber = new LottoNumberGenerator();
    private final List<Lotto> lottos;

    public Lottos(int countBuyableLotto) {
        this.lottos = makeLottos(countBuyableLotto);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> makeLottos(int countBuyableLotto) {
        List<Lotto> lottosList = new ArrayList<>();
        for (int i = INITIAL_NUMBER; i < countBuyableLotto; i++) {
            Lotto lotto = new Lotto(createLottoNumber);
            lottosList.add(lotto);
        }
        return lottosList;
    }
}
