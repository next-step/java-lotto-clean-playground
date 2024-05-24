package domain;

import java.util.List;

public class LottoRate {

    private final List<LottoTicket> tickets;
    private final WinningLotto winningLotto;

    public LottoRate(List<LottoTicket> tickets, WinningLotto winningLotto) {
        this.tickets = tickets;
        this.winningLotto = winningLotto;
    }

    public List<Integer> matchesMoney() {
        return List.of(
                Ranking.FIRST.getPrice(),
                Ranking.SECOND.getPrice(),
                Ranking.THIRD.getPrice(),
                Ranking.FOURTH.getPrice(),
                Ranking.FIFTH.getPrice()
        );
    }

    public double calculateRateOfReturn(int payMoney, int lottoBonusNumber) {
        int totalWinnings = tickets.stream()
                .mapToInt(ticket -> {
                    int matchedNumbers = winningLotto.getCount(ticket.getNumbers());
                    int matchedBonusNumbers = winningLotto.getBonusCount(ticket.getNumbers(), lottoBonusNumber);
                    return Ranking.valueOfCount(matchedNumbers, matchedBonusNumbers).getPrice();
                }).sum();
        return (double) totalWinnings / payMoney;
    }
}
