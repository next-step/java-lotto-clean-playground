package domain;

import java.util.*;

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
        if(length != TICKET_LENGTH) {
            throw new IllegalArgumentException("로또 티켓의 숫자는 " + TICKET_LENGTH + "개여야 합니다.");
        }
        Set<LottoNumber> ticketSet = new HashSet<>(lottoNumbers);
        if (ticketSet.size() != length) {
            throw new IllegalArgumentException("로또 티켓의 숫자는 중복될 수 없습니다.");
        }
    }

    public LottoRank getLottoRank(LottoTicket winnerTicket, LottoNumber bonusNumber) {
        int matchCount = getMatchCount(winnerTicket);
        if (matchCount == LottoRank.FIRST.getMatchingNumberCount() && Boolean.compare(containsBonusNumber(bonusNumber), LottoRank.FIRST.shouldMatchBonusBall()) != -1) return LottoRank.FIRST;
        if (matchCount == LottoRank.SECOND.getMatchingNumberCount() && Boolean.compare(containsBonusNumber(bonusNumber), LottoRank.SECOND.shouldMatchBonusBall()) != -1) return LottoRank.SECOND;
        if (matchCount == LottoRank.THIRD.getMatchingNumberCount() && Boolean.compare(containsBonusNumber(bonusNumber), LottoRank.THIRD.shouldMatchBonusBall()) != -1) return LottoRank.THIRD;
        if (matchCount == LottoRank.FOURTH.getMatchingNumberCount() && Boolean.compare(containsBonusNumber(bonusNumber), LottoRank.FOURTH.shouldMatchBonusBall()) != -1) return LottoRank.FOURTH;
        if (matchCount == LottoRank.FIFTH.getMatchingNumberCount() && Boolean.compare(containsBonusNumber(bonusNumber), LottoRank.FIFTH.shouldMatchBonusBall()) != -1) return LottoRank.FIFTH;
        return null;
    }

    private int getMatchCount(LottoTicket winnerTicket) {
        int matchCount = 0;
        for (LottoNumber number : winnerTicket.lottoNumbers) {
            matchCount += Boolean.compare(lottoNumbers.contains(number), false);
        }
        return matchCount;
    }

    private boolean containsBonusNumber(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
