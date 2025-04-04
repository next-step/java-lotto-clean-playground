package model.lotto;

import model.LottoNumber;
import model.Ranking;

import java.util.TreeSet;

public abstract class Lotto {

    private final TreeSet<LottoNumber> numbers;

    protected Lotto(TreeSet<LottoNumber> numbers) {
        this.numbers = numbers;
    }

    public Ranking calculateRanking(Lotto winningNumbers, LottoNumber bonusBall) {
        int matchingCount = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
        boolean hasMatchedBonusBall = numbers.contains(bonusBall);
        return Ranking.getRanking(matchingCount, hasMatchedBonusBall);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public int size() {
        return numbers.size();
    }

    public TreeSet<LottoNumber> getNumbers() {
        return new TreeSet<>(numbers);
    }
}
