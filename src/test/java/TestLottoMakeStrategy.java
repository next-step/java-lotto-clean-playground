import domain.Lotto;
import domain.LottoMakeStrategy;
import domain.Lottos;

import java.util.ArrayList;
import java.util.List;

public class TestLottoMakeStrategy implements LottoMakeStrategy {

    @Override
    public Lottos makeLottos() {
        return new Lottos(
                new ArrayList<Lotto>(
                        List.of(new Lotto(new ArrayList<Integer>(List.of(5, 14, 16, 35, 38, 44))))
                )
        );
    }

}
