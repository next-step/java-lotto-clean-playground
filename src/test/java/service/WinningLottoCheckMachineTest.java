package service;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.*;

public class WinningLottoCheckMachineTest {

    private LottoTicket winningNumbers;
    private LottoNumber bonusNumber;
    private WinningLottoCheckMachine winningLottoCheckMachine;

    @Test
    @DisplayName("보너스 번호 중복 시, 예외가 발생한다.")
    void testValidateBonusNumberThrowsExceptionWhenDuplicate() {
        LottoNumber duplicateBonusNumber = new LottoNumber(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new WinningLottoCheckMachine(winningNumbers, duplicateBonusNumber));
    }


    @BeforeEach
    void initialize(){

        winningNumbers = new LottoTicket(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        bonusNumber = new LottoNumber(7);

        winningLottoCheckMachine = new WinningLottoCheckMachine(winningNumbers,bonusNumber);

    }

    @Test
    @DisplayName("로또 당첨 번호 확인 기계 작동 테스트")
    void testOperateLottoCheckMachine(){
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addTicket(new LottoTicket(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))));
        lottoTickets.addTicket(new LottoTicket(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(7))));
        lottoTickets.addTicket(new LottoTicket(Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(8), new LottoNumber(9))));

        int purchaseAmount = 3000;

        LottoResult result = winningLottoCheckMachine.operateLottoCheckMachine(lottoTickets,purchaseAmount);

        assertThat(result.getWinningResult().get(LottoRank.FIRST)).isEqualTo(1);
        assertThat(result.getWinningResult().get(LottoRank.SECOND)).isEqualTo(1);
        assertThat(result.getWinningResult().get(LottoRank.THIRD)).isEqualTo(0);
    }


}
