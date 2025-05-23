package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Nested
    @DisplayName("입력된 지난 당첨번호를 정상적으로 로또 객체로 생성한다.")
    class createWinningLotto {

        @Test
        void LottoWinningNumber_입력된당첨번호로_로또객체생성() {
            String input = "1,2,3,4,5,6";
            LottoNumber bonusBall = new LottoNumber(7);

            WinningLotto winningLotto = new WinningLotto(LottoParser.parse(input),bonusBall);

            assertThat(winningLotto.getWinningNumbers().getNumbers())
                    .extracting(LottoNumber::getLottoNumber)
                    .asList()
                    .containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
        }
    }
}
