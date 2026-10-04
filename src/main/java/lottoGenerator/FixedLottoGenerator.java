package lottoGenerator;

import static domain.Lotto.LOTTO_SIZE;

import java.util.Collections;
import java.util.List;

public class FixedLottoGenerator implements LottoGenerator{
    private final List<Integer> lotto;

    public FixedLottoGenerator(List<Integer> lotto){
        this.lotto=lotto;
    }

    @Override
    public List<Integer> generateLotto() {
        return lotto;
    }
}
