package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoTicket {
    public static final int TICKET_LENGTH = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        Collections.sort(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private static void validate(List<LottoNumber> lottoNumbers) {
        int length = lottoNumbers.size();
        if (length != TICKET_LENGTH) {
            throw new IllegalArgumentException("로또 티켓의 숫자는 " + TICKET_LENGTH + "개여야 합니다.");
        }
        Set<LottoNumber> ticketSet = new HashSet<>(lottoNumbers);
        if (ticketSet.size() != length) {
            throw new IllegalArgumentException("로또 티켓의 숫자는 중복될 수 없습니다.");
        }
    }

    public LottoRank getLottoRank(LottoTicket winnerTicket, LottoNumber bonusNumber) {
        if (winnerTicket.lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
        return LottoRank.getLottoRank(getMatchCount(winnerTicket), lottoNumbers.contains(bonusNumber));
    }

    private int getMatchCount(LottoTicket winnerTicket) {
        int matchCount = 0;
        for (LottoNumber number : winnerTicket.lottoNumbers) {
            matchCount += Boolean.compare(lottoNumbers.contains(number), false);
        }
        return matchCount;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
