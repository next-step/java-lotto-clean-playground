package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final LottoNumber numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new LottoNumber(numbers);
    }

    public Rank countMatch(WinningNumbers winningNumbers) {
        return numbers.match(winningNumbers);
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    public static Lotto from(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력된 로또 번호가 비어 있습니다.");
        }

        List<Integer> numbers = Arrays.stream(input.split(","))
            .map(String::strip)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        return new Lotto(numbers);
    }
}
