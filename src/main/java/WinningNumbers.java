import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
    private final List<LottoNumber> numbers;

    public WinningNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        this.numbers = numbers;
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

}
