package domain.lottoTicket;

import java.util.List;

public class LottoTicket {
    private final List<Integer> lottoNumbers;

    public LottoTicket(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumber() {
        return lottoNumbers;
    }

}
