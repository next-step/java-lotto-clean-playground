package strategy;

import java.util.List;

public class FixNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate() {
        return List.of(1, 2, 3, 4, 5, 6);
    }
}
