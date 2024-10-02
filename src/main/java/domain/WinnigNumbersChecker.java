package domain;

import utils.WinningNumberFomatter;

import java.util.*;

import static domain.Rank.NONE;

public class WinnigNumbersChecker {
    Lottos lottos;
    BonusNumber bonusNumber;

    public WinnigNumbersChecker(Lottos lottos,BonusNumber bonusNumber) {
        this.lottos = lottos;
        this.bonusNumber = bonusNumber;
    }

    private int countMatchingNumbers(List<Integer> lotto) {
        List<Integer> winningNumbers = WinningNumberFomatter.formedWinningNumbers;

        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private Rank findRank(int matchCount, boolean matchBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matches(matchCount, matchBonus))
                .findFirst()
                .orElse(NONE);
    }

    public List<Rank> analizeResultToList() {
        List<Rank> resultList = new ArrayList<>();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = countMatchingNumbers(lotto.getLotto());
            boolean matchBonus = bonusNumber.match(lotto);

            resultList.add(findRank(matchCount,matchBonus));
        }

        return resultList;
    }
}
