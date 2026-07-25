package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto extends Numbers {

    public Lotto(List<Integer> numbers) {
        super(numbers);
    }

    public static Lotto from(List<String> rawNumbers) {
        try {
            return new Lotto(toNumbers(rawNumbers));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자로 입력해야 합니다.");
        }
    }

    private static List<Integer> toNumbers(List<String> rawNumbers) {
        return rawNumbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    protected String label() {
        return "로또 번호";
    }
}
