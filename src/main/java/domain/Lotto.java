package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return new Lotto(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    "로또 번호는 " + LOTTO_SIZE + "개여야 합니다."
            );
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        List<LottoNumber> uniqueNumbers = new ArrayList<>();

        for (LottoNumber number : numbers) {
            validateNotDuplicate(uniqueNumbers, number);
            uniqueNumbers.add(number);
        }
    }

    private void validateNotDuplicate(List<LottoNumber> numbers, LottoNumber number) {
        if (numbers.contains(number)) {
            throw new IllegalArgumentException(
                    "로또 번호는 중복일 수 없습니다."
            );
        }
    }

    public int calculateMatchCount(Lotto winningLotto) {
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
