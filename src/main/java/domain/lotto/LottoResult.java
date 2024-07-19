package domain.lotto;

import domain.common.Money;
import domain.lotto.dto.MatchResult;
import domain.lotto.dto.StatistsDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private static final String ALREADY_EXIST = "보너스 번호가 이미 당첨 번호에 존재합니다.";
    
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public LottoResult(LottoTicket winningTicket, LottoNumber bonusNumber) {
        checkBonusBallExist(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void checkBonusBallExist(LottoTicket winningTicket, LottoNumber bonusNumber) {
        if (winningTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException(ALREADY_EXIST);
        }
    }

    public Prize findPrize(LottoTicket lottoTicket) {
        final int match = lottoTicket.contains(winningTicket);
        final boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);
        return Prize.findByMatch(match, hasBonusNumber);
    }

    public StatistsDto makeStatistics(Money myMoney, List<LottoTicket> lottoTickets) {
        Map<Prize, Integer> prizes = initPrizes();

        lottoTickets.forEach(lottoTicket -> {
            Prize prize = findPrize(lottoTicket);
            prizes.put(prize, prizes.getOrDefault(prize, 0) + 1);
        });

        List<MatchResult> matchResults = getMathResult(prizes);
        Money total = calculateTotalMoney(prizes);
        double profitRate = (double) total.getAmount() / myMoney.getAmount();
        profitRate = Math.floor(profitRate * 100) / 100;
        return new StatistsDto(matchResults, profitRate);
    }

    private Map<Prize, Integer> initPrizes() {
        Map<Prize, Integer> prizes = new LinkedHashMap<>();
        prizes.put(Prize.FOURTH, 0);
        prizes.put(Prize.THIRD, 0);
        prizes.put(Prize.SECOND, 0);
        prizes.put(Prize.SECOND_BONUS_BALL, 0);
        prizes.put(Prize.FIRST, 0);
        return prizes;
    }

    private List<MatchResult> getMathResult(Map<Prize, Integer> prizes) {
        return prizes.keySet().stream()
            .filter(k -> k != Prize.LOSING_TICKET)
            .map(k -> new MatchResult(k.getMatch(), k.getMoney().getAmount(), prizes.get(k), k.hasBonusNumber()))
            .toList();
    }

    private Money calculateTotalMoney(Map<Prize, Integer> prizes) {
        int totalPrize = prizes.keySet().stream()
            .mapToInt(k -> k.getMoney().getAmount() * prizes.get(k))
            .sum();
        return new Money(totalPrize);
    }
}
