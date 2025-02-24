package domain.model;

import java.util.List;
import java.util.Comparator;

public record Lotto(List<LottoNumber> numbers) {
    public Lotto(List<LottoNumber> numbers) {
        this.numbers = List.copyOf(numbers.stream()
                .sorted(Comparator.comparingInt(LottoNumber::number))
                .toList());
    }
}
