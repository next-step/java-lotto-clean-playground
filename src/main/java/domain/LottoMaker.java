package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {

    public List<Lotto> autoMake(int lottoQuantity) {
        AutoNumberMaker autoNumberMaker = new AutoNumberMaker();

        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(autoNumberMaker.getLottoNumber());
            lottos.add(lotto);
        }

        return lottos;
    }

    public List<Lotto> manualMake(int lottoQuantity, List<List<Integer>> manulLottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoQuantity; i++) {
            Lotto lotto = new Lotto(manulLottoNumbers.get(i));
            lottos.add(lotto);
        }

        return lottos;
    }
}
