package view;

import domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @Test
    @DisplayName("테스트")
    public void a() {
        int money = 10000;
        Lotto lotto = new Lotto(10000);
        new OutputView().outputLottoSet(money, lotto.getLottoNumberSet());

    }
}
