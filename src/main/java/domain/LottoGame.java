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

    public int[] calculateWinningStatistics(WinningNumbers winningNumbers) {
        int[] matchCounts = new int[7]; // 0부터 6까지, 6개 일치일 때를 고려하여 크기를 7로 변경
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = calculateMatchCount(lotto.getLottoNumbers(), winningNumbers.getNumbers());
            matchCounts[matchCount] += 1;
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
        int totalPrize = matchCounts[3] * 5000 +
                matchCounts[4] * 50000 +
                matchCounts[5] * 1500000 +
                matchCounts[6] * 2000000000;
        int totalPrice = totalLottoCount * Lotto.PRICE_PER_TICKET;

        if (totalPrice == 0) {
            return profitRate;
        }

        profitRate = ((double) totalPrize / totalPrice);
        return profitRate;
    }
}