package model;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final NumbersGenerator numbersGenerator;
    private final List<Integer> numbers;

    public Lotto(NumbersGenerator numbersGenerators) {
        this.numbersGenerator = numbersGenerators;
        this.numbers = numbersGenerator.generate();
    }

    public Ranking calculateRanking(WinningNumbers winningNumbers) {
        int matchingCount = (int) numbers.stream()
                .filter(number -> winningNumbers.getLottos().contains(number))
                .count();
        return Ranking.getRanking(matchingCount);
    }

    public int size() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}
