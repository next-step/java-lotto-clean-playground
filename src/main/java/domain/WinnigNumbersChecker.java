package domain;

import utils.WinningNumberFomatter;

import java.util.*;

public class WinnigNumbersChecker {
    Lottos lottos;

    public WinnigNumbersChecker(Lottos lottos) {
        this.lottos = lottos;
    }

    private int countMatchingNumbers(List<Integer> lotto) {
        List<Integer> winningNumbers = WinningNumberFomatter.formedWinningNumbers;

        return (int) lotto.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public List<Integer> analizeResultToList() {
        List<Integer> resultList = new ArrayList<>();

        for (Lotto lotto : lottos.getLottos()) {
            resultList.add(countMatchingNumbers(lotto.getLotto()));
        }

        return resultList;
    }
}
