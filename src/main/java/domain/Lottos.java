package domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos = new ArrayList<>();
    private final LottoGenerator lottoGenerator = new LottoGenerator();

    public Lottos(List<List<Integer>> manualLottoNumbers, int autoLottoCount) {
        for (List<Integer> manualLottoNumber : manualLottoNumbers) {
            lottos.add(new Lotto(manualLottoNumber));
        }

        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(lottoGenerator.generate());
        }
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
