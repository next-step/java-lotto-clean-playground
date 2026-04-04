package number_generator;

import java.util.List;

public class From1to6NumberListGenerator implements LottoNumberListGenerator {
    @Override
    public List<Integer> generate() {
        return List.of(1, 2, 3, 4, 5, 6);
    }
}
