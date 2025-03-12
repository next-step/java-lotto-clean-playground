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

    public List<LottoRank> calculateRank(List<Lotto> lottos) {
        return lottos.stream()
                .map(this::getLottoRank)
                .toList();
    }

    public double calculateEarningsRate(List<LottoRank> lottoRanks) {
        int totalEarnings = calculateTotalEarnings(lottoRanks);
        int totalSpent = lottoRanks.size() * LottoConstants.LOTTO_TICKET_PRICE.getValue();
        return (double) totalEarnings / totalSpent;
    }

    private void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호 목록에 포함될 수 없습니다.");
        }
    }

    private LottoRank getLottoRank(Lotto lotto) {
        long matchCount = lotto.getLottoNumbers().stream()
                .filter(winningNumbers::contains)
                .count();

        boolean matchBonus = lotto.getLottoNumbers().contains(bonusNumber);

        return LottoRank.getLottoRank((int) matchCount, matchBonus);
    }
    
    private int calculateTotalEarnings(List<LottoRank> lottoRanks) {
        return lottoRanks.stream()
                .mapToInt(LottoRank::getPrice)
                .sum();
    }
}
