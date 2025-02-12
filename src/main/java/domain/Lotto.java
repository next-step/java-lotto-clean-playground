package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbersSize(numbers);
        this.numbers = convertToLottoNumberSet(numbers);
        validateDuplicateNumbers();
    }

    private Set<LottoNumber> convertToLottoNumberSet(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    private void validateNumbersSize(List<Integer> numbers) {
        if(numbers.size() != LOTTO_NUMBERS_SIZE){
            throw new IllegalArgumentException("로또 번호 6자리를 입력해주세요.");
        }
    }

    private void validateDuplicateNumbers() {
        if(this.numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("중복되지 않은 로또 번호들을 입력해주세요.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
    }
}
