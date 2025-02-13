package domain;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static constant.LottoConstant.LOTTO_NUMBERS_SIZE;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateDuplicateNumbers(numbers);
        this.numbers = numbers.stream()
                .sorted(Comparator.naturalOrder())
                .map(LottoNumber::new)
                .toList();
    }

    private void validateNumbersSize(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if(numberSet.size() != LOTTO_NUMBERS_SIZE){
            throw new IllegalArgumentException("로또 번호 6자리를 입력해주세요.");
        }
    }

    private void validateDuplicateNumbers(List<Integer> numbers) {
        if(numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("중복되지 않은 로또 번호들을 입력해주세요.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
