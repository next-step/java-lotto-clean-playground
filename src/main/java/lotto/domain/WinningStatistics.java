package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinningStatistics {
    private final List<Rank> ranks;

    public WinningStatistics(Lottos lottos, WinningNumbers winningNumbers) {
        this.ranks = createRanks(lottos, winningNumbers);
    }

    private List<Rank> createRanks(Lottos lottos, WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (Lotto lotto : lottos.getLottoList()) {
            ranks.add(findRank(lotto, winningNumbers));
        }
        return ranks;
    }

    // 등수 계산 로직
    private Rank findRank(Lotto lotto, WinningNumbers winningNumbers) {
        int matchCount = winningNumbers.countMatch(lotto); // 몇개 맞았는지
        boolean hasBonus = winningNumbers.matchesBonus(lotto); // 보너스 번호 맞았는지
        return Rank.of(matchCount, hasBonus);
    }

    // 수익률 계산 로직
    public double getProfitRate() {
        long totalPrize = 0;
        for (Rank rank : ranks) {
            totalPrize += rank.getPrize();
        }
        return (double) totalPrize / (ranks.size() * Money.LOTTO_PRICE);
    }

    public int countRank(Rank rank) {
        return Collections.frequency(ranks, rank);
    }
    //[학습] Collections.frequency(컬렉션, 값)은 "이 컬렉션 안에 이 값이 몇 개 있어?"를 바로 세어주는 메서드

}
