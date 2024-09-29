import domain.LottoMakeStrategy;

import java.util.ArrayList;
import java.util.List;

public class TestLottoMakeStrategy implements LottoMakeStrategy {

    @Override
    public List<Integer> makeLotto() {
        return new ArrayList<Integer>(List.of(5, 14, 16, 35, 38, 44));
    }

}
