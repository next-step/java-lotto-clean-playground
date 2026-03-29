package domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    private final List<Lotto> lottoNumber;

    public LottoNumber(int trialCount, LottoNumberGenerator generator) {
        List<Lotto> generatedLottos = new ArrayList<>();
        for (int i = 0; i < trialCount; i++) {
            generatedLottos.add(new Lotto(generator.generate()));
        }
        this.lottoNumber = generatedLottos;
    }

    public List<Lotto> getLottoNumber() {
        return Collections.unmodifiableList(lottoNumber);
    }
}
