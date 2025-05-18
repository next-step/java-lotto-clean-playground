import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 당첨 번호의 개수는 6개 입니다.");
        }
        this.numbers = numbers;
    }

    public Rank match(WinningNumbers winningNumbers) {
        int matchCount = countMatch(winningNumbers);
        boolean bonusMatch = hasBonusMatch(winningNumbers);

        return Rank.valueOf(matchCount, bonusMatch);
    }

    public int countMatch(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean hasBonusMatch(WinningNumbers winningNumbers) {
        return numbers.stream()
                .anyMatch(winningNumbers::isBonusBall);
    }


    public List<LottoNumber> getNumbers() {
        return numbers;
    }

}
