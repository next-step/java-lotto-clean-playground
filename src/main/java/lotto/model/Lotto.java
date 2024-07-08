package lotto.model;

import static lotto.global.Constants.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lotto.global.Rank;
import lotto.utils.generator.Generator;

public class Lotto {
    private final Set<Integer> numbers;

    public Lotto(final Set<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto from(final Generator generator) {
        Set<Integer> numbers = generator.generate(MIN_NUMBER, MAX_NUMBER, NUMBERS_SIZE);

        return new Lotto(numbers);
    }

    public Rank getRank(WinNumbers winNumbers) {
        Set<Integer> retain = new HashSet<>(numbers);
        retain.retainAll(winNumbers.getWinNumbers());

        return Rank.getRankByMatch(retain.size());
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers.toArray());
    }

}
