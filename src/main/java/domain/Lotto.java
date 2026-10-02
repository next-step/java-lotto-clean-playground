package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    private Lotto(List<LottoNumber> lotto) {
        validate(lotto);
        this.lotto = List.copyOf(lotto);
    }

    public static Lotto from(List<Integer> numbers) {
        validateNotNull(numbers);
        List<LottoNumber> lotto = new ArrayList<>();
        for (int number : numbers) {
            lotto.add(new LottoNumber(number));
        }
        return new Lotto(lotto);
    }

    private static void validateNotNull(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 입력되지 않았습니다.");
        }
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
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

    public List<LottoNumber> getNumbers() {
        return List.copyOf(lotto);
    }

    public int getCount(Lotto other) {
        List<LottoNumber> copy = new ArrayList<>(this.lotto);
        copy.retainAll(other.lotto);
        return copy.size();
    }
}
