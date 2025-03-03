package model;

import java.util.List;

public class LottoResult {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public LottoResult(List<Integer> winningNumbers, int bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호 목록에 포함될 수 없습니다.");
        }
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
