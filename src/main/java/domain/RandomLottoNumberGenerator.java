package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator implements LottoNumberGenerator {

    private static final int LOTTO_SIZE = 6;

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> pool = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(pool);
        return List.copyOf(pool.subList(0, LOTTO_SIZE));
    }
}
