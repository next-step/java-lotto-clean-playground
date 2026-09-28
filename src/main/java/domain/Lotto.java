package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<Integer> numbers) {
        validateNotNull(numbers);

        List<LottoNumber> lotto=new ArrayList<>();
        for (int number : numbers) {
            lotto.add(new LottoNumber(number));
        }
        validate(lotto);
        this.lotto = lotto;
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
    }
    private void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 입력되지 않았습니다.");
        }
    }
    private void validateDuplicates(List<LottoNumber> numbers) {
        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 " + LOTTO_SIZE + "개여야 합니다. (입력 개수: " + numbers.size() + "개)");
        }
    }

    public List<Integer> getNumbers() {
        List<Integer> numbers=new ArrayList<>();
        for (LottoNumber lottoNumber : lotto) {
            numbers.add(lottoNumber.getLottoNumber());
        }
        return numbers;
    }
}
