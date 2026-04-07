package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private final List<LottoNumber> numbers;

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public int getWinningNumberMatchCount(List<LottoNumber> winningNumbers) {
        int matchCount = 0;
        for (LottoNumber number : winningNumbers) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }

        return matchCount;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 숫자는 6개여야 해요.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("당첨 숫자에는 중복된 값이 들어올 수 없어요.");
        }
    }
}
