package model;

import java.util.List;

public class LottoResult {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoResult(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<LottoRank> calculateRank(List<Lotto> lottos) {
        return lottos.stream()
                .map(this::getLottoRank)
                .toList();
    }

    private LottoRank getLottoRank(Lotto lotto) {
        long matchCount = lotto.getLottoNumbers().stream()
                .filter(winningNumbers::contains)
                .count();

        boolean matchBonus = lotto.getLottoNumbers().contains(bonusNumber);

        return LottoRank.getLottoRank((int) matchCount, matchBonus);
    }
}
