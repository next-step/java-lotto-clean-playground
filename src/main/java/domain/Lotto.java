package domain;

import java.util.*;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        this.lottoNumbers = List.copyOf(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 정확히 " + LOTTO_SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public static int countMatchingNumbers(Lotto lotto1, Lotto lotto2) {
        return (int) lotto1.getLottoNumbers().stream()
                .filter(lotto2::contains)
                .count();
    }
}
