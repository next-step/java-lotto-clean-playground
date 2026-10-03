package domain;

import java.util.ArrayList;
import java.util.List;
import lottoGenerator.LottoGenerator;

public class LottoMachine {
    private final LottoGenerator lottoGenerator;
    List<Lotto> lottoList = new ArrayList<>();

    public LottoMachine(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public List<Lotto> purchase(int lottoCount) {
        for (int i = 0; i < lottoCount; i++) {

            lottoList.add(Lotto.from(lottoGenerator.generateLotto()));
        }
        return lottoList;
    }

    public void manualSelection(List<Integer> Lottonumbers) {
            lottoList.add(Lotto.from(Lottonumbers));
    }
}
