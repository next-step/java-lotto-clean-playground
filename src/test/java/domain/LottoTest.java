package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    @DisplayName("로또숫자 생성")
    public void a() {
        //given
        int expectedMoney = 10000;
        int expectedLottoCount = 10;
        Lotto lotto = new Lotto(10000);

        //when
        lotto.makeLottoNumberSet(10000);

        Random random = new Random(24);

        Set<Integer> expectedLottoNumber = new HashSet<>();

        for(int i = 0; i < 6; i ++) {
            expectedLottoNumber.add(random.nextInt(1,46));
        }

        Set<Integer> lottoNumber = lotto.getLottoNumberSet().get(0);

        //then
        assertAll(
                () -> assertThat(expectedLottoCount).isEqualTo(lotto.getLottoCount()),
                () -> assertThat(expectedLottoNumber).isEqualTo(lottoNumber)
        );
    }
}
