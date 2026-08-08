package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = convert(numbers);

        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);

        this.numbers = sort(lottoNumbers);
    }

    private List<LottoNumber> convert(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LottoRules.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 6개여야 합니다."
            );
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(
                    "[ERROR] 로또 번호는 중복될 수 없습니다."
            );
        }
    }

    private List<LottoNumber> sort(List<LottoNumber> numbers) {
        List<LottoNumber> sortedNumbers = new ArrayList<>(numbers);
        sortedNumbers.sort(LottoNumber::compareTo);
        return sortedNumbers;
    }

    public int countMatchingNumbers(Lotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto.numbers::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
