package domain;

import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 숫자 개수가 " + LOTTO_NUMBER_COUNT + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> numbers) {
        long distinctCount = numbers.stream()
                .distinct()
                .count();

        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException("중복된 로또 숫자가 존재합니다.");
        }
    }

    public List<LottoNumber> getNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int getMatchCount(Lotto other) {
        return (int) lottoNumbers.stream()
                .filter(other::contains)
                .count();
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
}
