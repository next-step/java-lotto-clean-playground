package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또숫자 생성")
    public void a() {
        //given
        Random random = new Random(24);
        int Money = 10000;
        int expectedLottoCount = 10;
        Lotto lotto = new Lotto(10000);

        //when
        lotto.makeLottoNumberTickets(Money);

        List<Integer> expectedLottoTicket = new ArrayList<>();

        for(int i = 0; i < 6; i ++) {
            expectedLottoTicket.add(random.nextInt(1,45));
        }

        Collections.sort(expectedLottoTicket);

        List<Integer> lottoNumber = lotto.getLottoNumberTickets().get(0);

        //then
        assertAll(
                () -> assertThat(expectedLottoCount).isEqualTo(lotto.getLottoCount()),
                () -> assertThat(expectedLottoTicket).isEqualTo(lottoNumber)
        );
    }
}
