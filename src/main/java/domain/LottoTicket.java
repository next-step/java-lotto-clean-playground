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

    private static void validate(List<LottoNumber> lottoNumbers) {
        int length = lottoNumbers.size();
        Set<LottoNumber> ticketSet = new HashSet<>(lottoNumbers);
        if (ticketSet.size() != length) {
            throw new RuntimeException();
        }
    }

    public Count getMatchCount(LottoTicket winnerTicket) {
        int matchCount = 0;
        for (LottoNumber number : winnerTicket.lottoNumbers) {
            matchCount += Boolean.compare(lottoNumbers.contains(number), false);
        }
        return new Count(matchCount);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
