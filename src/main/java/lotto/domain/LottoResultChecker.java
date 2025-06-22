package lotto.domain;

import java.util.EnumMap;
import java.util.Map;

public class LottoResultChecker {

  private final LottoResult winningNumbers;
  private final LottoNumber bonusBall;

  public LottoResultChecker(final LottoResult winningNumbers, final LottoNumber bonusBall) {
    validateNumber(winningNumbers, bonusBall);
    this.winningNumbers = winningNumbers;
    this.bonusBall = bonusBall;
  }

  public WinningResult matchLottos(Lottos userLottos) {
    Map<Rank, Integer> rankCounts = new EnumMap<>(Rank.class);

    for (Rank rank : Rank.values()) {
      rankCounts.put(rank, 0);
    }

    for (Lotto lotto : userLottos) {
      Rank rank = getRankByCount(lotto);
      rankCounts.put(rank, rankCounts.get(rank) + 1);
    }

    return new WinningResult(rankCounts);
  }

  private Rank getRankByCount(final Lotto lotto) {
    return Rank.valueOf(matchCount(lotto), hasBonusBall(lotto));
  }

  private boolean hasBonusBall(final Lotto lotto) {
    return lotto.numbers().contains(bonusBall);
  }

  private int matchCount(Lotto userLotto) {
    return winningNumbers.matchCount(userLotto);
  }

  private void validateNumber(final LottoResult winningNumbers, final LottoNumber bonusBall) {
    if (winningNumbers.numbers().contains(bonusBall)) {
      throw new RuntimeException("보너스 번호는 기존 번호와 중복될 수 없습니다.");
    }
  }
}
