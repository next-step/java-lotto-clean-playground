package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LottoResult {
    private final Map<LottoRank, Integer> ticketCountOfEachRank;

    public LottoResult(List<LottoRank> lottoRankOfEachTicket) {
        List<Integer> matchingTicketCounts = new ArrayList<>();
        for (LottoRank lottoRank: LottoRank.values()) {
            matchingTicketCounts.add(Collections.frequency(lottoRankOfEachTicket, lottoRank));
        }
        ticketCountOfEachRank = new HashMap<>();
        for(int i = 0; i < matchingTicketCounts.size(); i++) {
            ticketCountOfEachRank.put(LottoRank.values()[i], matchingTicketCounts.get(i));
        }
    }

    public double getProfitRate(Price price) {
        int totalProfit = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            totalProfit += lottoRank.getPrizeMoney() * ticketCountOfEachRank.get(lottoRank);
        }
        return price.calculateProfitRate(totalProfit);
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
