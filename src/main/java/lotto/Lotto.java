package lotto;

import java.util.List;

public record Lotto(LottoNumbers numbers) {
    public List<LottoNumber> getNumbers() {
        return numbers.value();
    }
}
