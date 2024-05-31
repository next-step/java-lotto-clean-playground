package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WinningLottoTest {

    @Test
    void 당첨번호조회_테스트() {

        // given
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> ticketNumber1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> ticketNumber2 = List.of(3, 4, 5, 6, 7, 8);

        // when
        int expectCount1 = winningLotto.getCount(ticketNumber1);
        int expectCount2 = winningLotto.getCount(ticketNumber2);

        // then
        Assertions.assertAll(
                () -> assertEquals(6, expectCount1),
                () -> assertEquals(4, expectCount2)
        );
    }

    @Test
    void 보너스볼_카운트_테스트() {

        // given
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> ticketNumber1 = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> ticketNumber2 = List.of(3, 4, 5, 6, 7, 8);
        int bonusNumber = 7;

        // when
        int bonusCount1 = winningLotto.getBonusCount(ticketNumber1, bonusNumber);
        int bonusCount2 = winningLotto.getBonusCount(ticketNumber2, bonusNumber);

        // then
        Assertions.assertAll(
                () -> assertEquals(1, bonusCount2),
                () -> assertEquals(0, bonusCount1)
        );
    }
}