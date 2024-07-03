package domain.lotto.service;

import domain.common.Money;
import domain.lotto.LottoResult;
import domain.lotto.LottoTicket;
import domain.lotto.Prize;
import domain.lotto.dto.MatchResult;
import domain.lotto.dto.StatistsDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoService {

    public StatistsDto makeStatistics(Money myMoney, LottoResult lottoResult, List<LottoTicket> lottoTickets) {
        Map<Prize, Integer> prizes = getPrizes();
        
        lottoTickets.forEach(lottoTicket -> {
            Prize prize = lottoResult.findPrize(lottoTicket);
            prizes.put(prize, prizes.getOrDefault(prize, 0) + 1);
        });
        
        List<MatchResult> matchResults = getMathResult(prizes);
        Money total = calculateTotalMoney(prizes);
        double profitRate = (double) total.getAmount() / myMoney.getAmount();
        profitRate = Math.floor(profitRate * 100) / 100;
        return new StatistsDto(matchResults, profitRate);
    }

    private Map<Prize, Integer> getPrizes() {
        Map<Prize, Integer> prizes = new LinkedHashMap<>();
        prizes.put(Prize.FOURTH, 0);
        prizes.put(Prize.THIRD, 0);
        prizes.put(Prize.SECOND, 0);
        prizes.put(Prize.FIRST, 0);
        return prizes;
    }

    private List<MatchResult> getMathResult(Map<Prize, Integer> prizes) {
        List<MatchResult> matchResults = new ArrayList<>();
        for (Map.Entry<Prize, Integer> prizeIntegerEntry : prizes.entrySet()) {
            Prize prize = prizeIntegerEntry.getKey();
            Integer match = prize.getMatch();
            Money money = prize.getMoney();
            if (prize == Prize.LOSING_TICKET) continue;

            Integer count = prizeIntegerEntry.getValue();
            matchResults.add(new MatchResult(match, money.getAmount(), count));
        }
        return matchResults;
    }
    
    private Money calculateTotalMoney(Map<Prize, Integer> prizes) {
        Money total = Money.ZERO;
        for (Map.Entry<Prize, Integer> prizeIntegerEntry : prizes.entrySet()) {
            Prize prize = prizeIntegerEntry.getKey();
            if (prize == Prize.LOSING_TICKET) continue;

            Money money = prize.getMoney();
            Integer count = prizeIntegerEntry.getValue();
            total = total.plus(money.multiply(count));
        }
        return total;
    }
}
