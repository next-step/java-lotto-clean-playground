package domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> lottoNumber;

    public LottoTickets(int trialCount, LottoNumberGenerator generator) {
        List<Lotto> generatedLottoNumber = new ArrayList<>();
        for (int i = 0; i < trialCount; i++) {
            generatedLottoNumber.add(new Lotto(generator.generate()));
        }
        this.lottoNumber = generatedLottoNumber;
    }

    public List<Lotto> getLottoNumber() {
        return Collections.unmodifiableList(lottoNumber);
    }
}

