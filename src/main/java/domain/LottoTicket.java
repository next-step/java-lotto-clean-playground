package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private final LottoNumbers numbers;

    public LottoTicket(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public Rank countMatch(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchCount = (int) numbers.getNumbers().stream()
            .filter(winningNumbers.getWinningNumbers().getNumbers()::contains)
            .count();
        boolean isBonusMatched = numbers.getNumbers().contains(bonusNumber.getNumber());
        return Rank.from(matchCount, isBonusMatched);
    }

    public List<Integer> getNumbers() {
        return numbers.getNumbers();
    }

    public static LottoTicket from(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력된 로또 번호가 비어 있습니다.");
        }

        List<Integer> numbers = Arrays.stream(input.split(","))
            .map(String::strip)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }
}
