package domain.lotto;

import java.util.List;

public class LottosGenerator {
    public static Lottos generateLottos(List<List<Integer>> manualNumbers, int autoCount) {
        Lottos lottos = new Lottos();

        addManualLottos(lottos, manualNumbers);
        addAutoLottos(lottos, autoCount);

        return lottos;
    }

    private static void addManualLottos(Lottos lottos, List<List<Integer>> manualNumbers) {
        for (List<Integer> manualNumber : manualNumbers) {
            List<LottoNumber> lottoNumbers = manualNumber.stream()
                    .map(LottoNumber::new)
                    .toList();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    private static void addAutoLottos(Lottos lottos, int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
    }
}
