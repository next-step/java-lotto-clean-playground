import java.util.List;

public class WinningNumbers {
    private final List<LottoNumber> numbers;
    private final LottoNumber bonusBall;

    public WinningNumbers(List<LottoNumber> numbers, LottoNumber bonusBall) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        if (numbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복될 수 없습니다.");
        }

        this.numbers = numbers;
        this.bonusBall = bonusBall;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public boolean isBonusBall(LottoNumber number) {
        return bonusBall.equals(number);
    }
}
