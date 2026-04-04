package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> ticketCountOfEachRank;

    public LottoResult(List<Integer> matchingTicketCounts) {
        validate(matchingTicketCounts);
        ticketCountOfEachRank = new HashMap<>();
        for(int i = 0; i < matchingTicketCounts.size(); i++) {
            ticketCountOfEachRank.put(LottoRank.values()[i], matchingTicketCounts.get(i));
        }
    }

    public int getMatchCount(LottoRank rank) {
        Integer matchCount = ticketCountOfEachRank.get(rank);
        if (matchCount == null) {
            throw new IllegalArgumentException("존재하지 않는 등수입니다.");
        }
        return matchCount;
    }

    private void validate(List<Integer> matchingTicketCounts) {
        if (matchingTicketCounts.size() != LottoRank.values().length) {
            throw new IllegalArgumentException("등수 종류의 수가 올바르지 않습니다.");
        }
    }
}
