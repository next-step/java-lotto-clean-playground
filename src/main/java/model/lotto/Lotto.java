package model.lotto;

import model.BonusBall;
import model.Ranking;

import java.util.TreeSet;

public class Lotto {

    private final TreeSet<Integer> numbers;

    protected Lotto(TreeSet<Integer> numbers) {
        this.numbers = numbers;
    }

    public Ranking calculateRanking(Lotto winningNumbers, BonusBall bonusBall) {
        int matchingCount = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
        boolean hasMatchedBonusBall = numbers.contains(bonusBall.getBonusNumber());
        return Ranking.getRanking(matchingCount, hasMatchedBonusBall);
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public int size() {
        return numbers.size();
    }

    public TreeSet<Integer> getNumbers() {
        return new TreeSet<>(numbers);
    }
}
