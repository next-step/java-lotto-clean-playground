package model;

import java.util.List;

public record Lotto(List<LottoNumber> numbers) {

    @Override
    public String toString() {
        return numbers.toString();
    }
}
