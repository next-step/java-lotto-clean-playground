package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers extends Numbers {

    public WinningNumbers(List<Integer> numbers) {
        super(numbers);
    }

    public static WinningNumbers from(List<String> rawNumbers) {
        try {
            return new WinningNumbers(toNumbers(rawNumbers));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자로 입력해야 합니다.");
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
        return "당첨 번호";
    }

    public Rank match(Lotto lotto) {
        int matchCount = countMatch(lotto);
        return Rank.from(matchCount);
    }

    private int countMatch(Lotto lotto) {
        List<Integer> winning = getNumbers();
        return (int) lotto.getNumbers().stream()
                .filter(winning::contains)
                .count();
    }
}
