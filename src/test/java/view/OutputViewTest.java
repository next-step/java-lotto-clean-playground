package view;

import domain.Lotto;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @Test
    @DisplayName("로또 생성 확인 메서드")
    public void a() {
        // given
        int money = 10000;

        //when
        Lotto lotto = new Lotto(money);
        List<Integer> num = lotto.getLottoNumberList();

        //then
        new OutputView().outputLottoTickets(lotto);
    }
}
