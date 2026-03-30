package domain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoTickets {

    private final List<Lotto> lottoTickets;

    public LottoTickets(int trialCount, LottoNumberGenerator generator) {
        List<Lotto> generatedLottoNumber = new ArrayList<>();
        for (int i = 0; i < trialCount; i++) {
            generatedLottoNumber.add(new Lotto(generator.generate()));
        }
        this.lottoTickets = generatedLottoNumber;
    }

    public List<Lotto> getLottoNumber() {
        return Collections.unmodifiableList(lottoTickets);
    }
}

