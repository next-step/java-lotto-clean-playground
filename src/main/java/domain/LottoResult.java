package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<LottoRank, Integer> ticketCountOfEachRank;

    public LottoResult(List<Integer> matchingTicketCounts) {
        validate(matchingTicketCounts);
        ticketCountOfEachRank = new HashMap<>();
        for(int i = 0; i < matchingTicketCounts.size(); i++) {
            ticketCountOfEachRank.put(LottoRank.values()[i], matchingTicketCounts.get(i));
        }
    }

    private void validate(List<Integer> matchingTicketCounts) {
        if (matchingTicketCounts.size() != LottoRank.values().length) {
            throw new IllegalArgumentException("등수 종류의 수가 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult lottoResult = (LottoResult) o;
        return Objects.equals(ticketCountOfEachRank, lottoResult.ticketCountOfEachRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketCountOfEachRank);
    }

    public int getMatchCount(LottoRank rank) {
        return ticketCountOfEachRank.get(rank);
    }
}
