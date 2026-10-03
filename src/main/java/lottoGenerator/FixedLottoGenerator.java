package lottoGenerator;

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
