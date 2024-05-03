package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Lotto(List<LottoNumber> numbers) {

    public static final int LOTTO_SIZE = 6;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        List<LottoNumber> modifiable = new ArrayList<>(numbers);
        Collections.sort(modifiable);
        this.numbers = modifiable;
    }

    public static Lotto from(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new Lotto(lottoNumbers);
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    private void validateDuplication(List<LottoNumber> numbers) {
        long duplicatedSize = numbers.stream().distinct().count();
        if (numbers.size() != duplicatedSize) {
            throw new IllegalArgumentException("로또는 중복되지 않는 6개의 숫자로 이루어져야 합니다.");
        }
    }

    public Rank matchRank(Lotto other) {
        int matchCount = (int) numbers.stream()
                .filter(other.numbers()::contains)
                .count();
        return Rank.of(matchCount);
    }

    @Override
    public List<LottoNumber> numbers() {
        return List.copyOf(numbers);
    }
}
