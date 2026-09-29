package domain.purchase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return new Lotto(lottoNumbers);
    }

    public int countMatches(Lotto winningLotto) {
        int count = 0;
        for (LottoNumber number : numbers) {
            count += winningLotto.match(number);
        }
        return count;
    }

    private int match(LottoNumber number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
