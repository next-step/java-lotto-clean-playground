package lotto.mock;

import java.util.List;

import lotto.generator.NumberGenerator;

public class Lotto7NumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generateLottoNum() {
        return List.of(1, 2, 3, 4, 5, 6, 7);
    }
}
