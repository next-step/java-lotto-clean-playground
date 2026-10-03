package domain;

import java.util.ArrayList;
import java.util.List;
import lottoGenerator.LottoGenerator;
import lottoGenerator.RandomLottoGenerator;

public class LottoMachine {
    List<Lotto> lottoList = new ArrayList<>();


    public List<Lotto> purchase(int lottoCount) {
        final LottoGenerator lottoGenerator = new RandomLottoGenerator();
        for (int i = 0; i < lottoCount; i++) {
            lottoList.add(Lotto.from(lottoGenerator.generateLotto()));
        }
        return lottoList;
    }

    public void manualSelection(List<Integer> Lottonumbers) {
        lottoList.add(Lotto.from(Lottonumbers));
    }
}
