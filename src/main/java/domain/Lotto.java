package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int SIZE = 6;
    private static final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
    private final List<LottoNumber> numbers;





    public Lotto(List<LottoNumber> numbers) {
        validateNumber(numbers);
        this.numbers = numbers;
    }

    private void validateNumber(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호의 갯수는 6이여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        Set<LottoNumber> setNumbers = new HashSet<>(numbers);

        if (setNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다");
        }
    }

    public static Lotto creatAutoLotto() {
        return new Lotto(lottoNumberGenerator.generateLottoNumbers());
    }

    public List<LottoNumber> getLottoNumbers() {
        return numbers;
    }

    public static Lotto from(List<Integer> numbers) {
        return new Lotto(
                numbers.stream()
                        .map(LottoNumber::new)
                        .toList()
        );
    }

}
