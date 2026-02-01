package lotto.domain.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {

    public static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public static Lotto from(List<LottoNumber> numbers) {
        validateDuplicate(numbers);
        return new Lotto(numbers);
    }

    private Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public int countMatch(Lotto other) { // 겹치는 수 반환
        return (int) other.getNumbers().stream()
            .filter(this.getNumbers()::contains)
            .count();
    }
    public boolean contains(LottoNumber number) {
        return lottoNumbers.contains(number);
    }
    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }
    private static void validateDuplicate(List<LottoNumber> rawNumbers) {
        if (rawNumbers.size() != new HashSet<>(rawNumbers).size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
