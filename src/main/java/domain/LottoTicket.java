package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class LottoTicket {
    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    static private void validate(List<LottoNumber> lottoNumbers) {
        int length = lottoNumbers.size();
        Set<LottoNumber> ticketSet = new HashSet<>(lottoNumbers);
        if (ticketSet.size() != length) {
            throw new RuntimeException();
        }
    }

    public int getCorrectCount(LottoTicket winnerTicket) {
        int count = 0;
        for (LottoNumber number : winnerTicket.lottoNumbers) {
            count += Boolean.compare(lottoNumbers.contains(number), false);
        }
        return count;
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }
}
