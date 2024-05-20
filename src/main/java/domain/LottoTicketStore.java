package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTicketStore {

    private final List<Integer> lottoNumber;

    public List<Integer> getLottoNumber() {
        return new ArrayList<>(lottoNumber);
    }

    public LottoTicketStore(NumberGenerator numberGenerator) {
        this.lottoNumber = numberGenerator.generateRandomNumber();
    }

}
