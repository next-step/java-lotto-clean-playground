package domain.purchase;

import domain.lotto.Lotto;
import domain.lotto.LottoNumberGenerator;
import domain.lotto.LottoNumbers;
import domain.lotto.Lottos;
import java.util.ArrayList;
import java.util.List;

public class LottoFactory {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoFactory(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lotto createManualLotto(String numbers) {
        LottoNumbers lottoNumbers = LottoNumbers.from(numbers);
        return new Lotto(lottoNumbers);
    }

    public Lottos createLottos(List<String> manualNumbers, int autoCount) {
        List<Lotto> allLottos = new ArrayList<>();

        for (String numbers : manualNumbers) {
            allLottos.add(createManualLotto(numbers));
        }

        for (int i = 0; i < autoCount; i++) {
            allLottos.add(createAutoLotto());
        }

        return new Lottos(allLottos);
    }

    private Lotto createAutoLotto() {
        LottoNumbers lottoNumbers = lottoNumberGenerator.generate();
        return new Lotto(lottoNumbers);
    }
}
