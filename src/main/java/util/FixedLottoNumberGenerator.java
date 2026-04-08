package util;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {
    List<Integer> sequence;

    public FixedLottoNumberGenerator(List<Integer> sequence) {
        this.sequence = sequence;
    }

    public List<Integer> generateNumbers() {
        return sequence;
    }
}
