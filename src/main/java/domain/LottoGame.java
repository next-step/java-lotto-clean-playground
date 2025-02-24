package domain;

import domain.model.Lotto;
import domain.strategy.AutoGenerate;
import domain.strategy.LottoStrategy;

import java.util.*;

public class LottoGame {
    private final List<Lotto> purchasedLottoes = new ArrayList<>();
    private final Map<LottoRank, Integer> rankResults = new HashMap<>();

    public LottoGame() {
        initializeRankResults();
    }

    public void buyManualLottoes(List<Lotto> lottoes) {
        purchasedLottoes.addAll(lottoes);
    }

    public void buyAutoLottoes(int buyCount) {
        LottoStrategy strategy = new AutoGenerate();
        List<Lotto> lottoes = new ArrayList<>();

        for (int count = 0; count < buyCount; count++) {
            lottoes.add(strategy.generateLotto(null));
        }

        if (lottoes.isEmpty()) {
            throw new IllegalStateException("생성된 자동 로또가 없습니다.");
        }

        purchasedLottoes.addAll(lottoes);
    }

    public int getGameTotalCount(int money) {
        return money / LottoConstants.LOTTO_PRICE;
    }

    public Map<LottoRank, Integer> calculateResults(Lotto winningLotto, int bonusNumber) {
        for (Lotto purchasedLotto : purchasedLottoes) {
            LottoRank rank = determineLottoRank(purchasedLotto, winningLotto, bonusNumber);
            rankResults.put(rank, rankResults.getOrDefault(rank, 0) + 1);
        }
        return rankResults;
    }

    private LottoRank determineLottoRank(Lotto purchasedLotto, Lotto winningLotto, int bonusNumber) {
        int matchCount = countMatchingNumber(purchasedLotto, winningLotto);
        boolean hasBonus = purchasedLotto.numbers().contains(bonusNumber);
        return LottoRank.getMatchedRankByMatchCount(matchCount, hasBonus);
    }

    private int countMatchingNumber(Lotto purchasedLotto, Lotto winningLotto) {
        return Long.valueOf(purchasedLotto.numbers()
                .stream()
                .filter(winningLotto.numbers()::contains)
                .count())
                .intValue();
    }

    private void initializeRankResults() {
        List<LottoRank> ranks = Arrays.asList(LottoRank.values());
        Collections.reverse(ranks);

        for (LottoRank rank : ranks) {
            rankResults.put(rank, 0);
        }
    }

    public List<Lotto> getPurchasedLottoes() {
        return purchasedLottoes;
    }

    public Double calculatePrizeRate() {
        long totalPrize = 0L;

        for (Map.Entry<LottoRank, Integer> entry : rankResults.entrySet()) {
            LottoRank rank = entry.getKey();
            int count = entry.getValue();

            totalPrize += (long) rank.getPrizeMoney() * count;
        }

        int totalCost = purchasedLottoes.size() * LottoConstants.LOTTO_PRICE;

        return (double) totalPrize / totalCost;
    }
}
