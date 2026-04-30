package domain;

import java.util.List;

public class FixedLottoGenerator implements LottoGenerator {
    List<LottoNumber> numbers;

    public FixedLottoGenerator(List<Integer> sequence) {
        this.numbers = sequence.stream().map(LottoNumber::new).toList();
    }

    public List<LottoNumber> generateNumbers() {
        return numbers;
    }
}
