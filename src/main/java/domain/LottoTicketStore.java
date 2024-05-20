package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketStore {

    private final List<Integer> lottoNumber;

    public LottoTicketStore(NumberGenerator numberGenerator) {
        this.lottoNumber = numberGenerator.generateRandomNumber();
    }

    public List<Integer> getLottoNumber() {
        return new ArrayList<>(lottoNumber);
    }
}
