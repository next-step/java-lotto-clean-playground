package domain;

import view.OutputView;

import java.util.List;

public class LottoGame {
    private int lottoTotalPrice;
    private Lottos lottos;

    public LottoGame(int lottoTotalPrice) {
        this.lottoTotalPrice = lottoTotalPrice;
        this.lottos = new Lottos(calculateLottoAmount());
    }


    public int calculateLottoAmount() {
        return lottoTotalPrice / Lotto.PRICE_PER_TICKET;
    }

    public int getLottoTotalPrice() {
        return lottoTotalPrice;
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

    public int[] calculateWinningStatistics(WinningNumbers winningNumbers, int bonusBall) {
        int[] matchCounts = new int[8];
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = calculateMatchCount(lotto.getLottoNumbers(), winningNumbers.getNumbers());
            boolean hasBonusBall = lotto.getLottoNumbers().contains(bonusBall);
            matchCounts[matchCount + (hasBonusBall ? 1 : 0)] += 1;
        }
        return matchCounts;
    }

    private int calculateMatchCount(List<Integer> numbers, List<Integer> winningNumbers) {
        int matchCount = 0;
        for (Integer number : numbers) {
            if (winningNumbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    public static double calculateProfitRate(int[] matchCounts, int totalLottoCount) {
        double profitRate = 0;
        double totalPrize = matchCounts[3] * 5000 +
                matchCounts[4] * 50000 +
                matchCounts[5] * 1500000 +
                matchCounts[6] * 2000000000;
        double totalPrice = totalLottoCount * Lotto.PRICE_PER_TICKET;

        if (totalPrice == 0) {
            return 0;
        }

        profitRate = ((double) totalPrize / totalPrice);
        return profitRate;
    }
}