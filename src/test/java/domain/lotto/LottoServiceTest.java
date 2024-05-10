package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.lottoNumber.LottoNumber;
import domain.lottoTicket.LottoTicket;
import domain.lottoTicket.LottoTicketService;
import domain.lottoWinningStatistics.LottoWinningStatisticsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

public class LottoServiceTest {
    private final LottoService lottoService = new LottoService(new InputView(), new OutputView());
    private final LottoTicketService lottoTicketService = new LottoTicketService();
    private final LottoWinningStatisticsService lottoWinningStatisticsService = new LottoWinningStatisticsService();
    private final int MONEY = 14000;
    private final int LOTTO_PRICE = 1000;

    @Test
    @DisplayName("로또 티켓 세기")
    public void countLottoTicketsTest() {
        //given
        int expectedLottoTicketCount = MONEY / LOTTO_PRICE;
        //when

        //then
        assertThat(expectedLottoTicketCount).isEqualTo(lottoService.countLottoTickets(MONEY));
    }

    @Test
    @DisplayName("로또 티켓 묶음 생성 - 비교 불가 따라서 출력 대체")
    public void generateLottoTicketListTest() {
        //given
        Lotto lotto = new Lotto(Integer.toString(MONEY));

        List<LottoTicket> expectedLottoTickets = new ArrayList<>();

        for(int i = 0; i < lottoService.countLottoTickets(lotto.getLottoMoney()); i++) {
            LottoTicket lottoTicket = lottoTicketService.generateLottoTicket();
            expectedLottoTickets.add(lottoTicket);
        }

        //when
        List<LottoTicket> lottoTickets = lottoService.generateLottoTicketList(lotto);

        //then
        System.out.println("수동 생성한 로또 티켓 묶음");
        lottoService.printLottoTickets(lotto, expectedLottoTickets);
        System.out.println("\n로또 서비스로 만든 로또 티켓 묶음");
        lottoService.printLottoTickets(lotto, lottoTickets);
    }

    @Test
    @DisplayName("로또 티켓 합치기 테스트")
    public void mertgeLottoTicketsTest() {
        //given
        LottoTicket lottoTicket1 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 6)));
        LottoTicket lottoTicket2 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 7)));
        LottoTicket lottoTicket3 = new LottoTicket(new LottoNumber(List.of(1, 2, 3, 4, 5, 8)));
        List<LottoTicket> lottoTickets1 = List.of(lottoTicket1, lottoTicket2);
        List<LottoTicket> lottoTickets2 = List.of(lottoTicket3);

        //when
        List<LottoTicket> lottoTickets = lottoService.mergeLottoTickets(lottoTickets1, lottoTickets2);

        //then
        assertAll(
                () -> assertThat(lottoTicket1.getLottoTicketNumber().getLottoNumber()).isEqualTo(lottoTickets.get(0).getLottoTicketNumber().getLottoNumber()),
                () -> assertThat(lottoTicket2.getLottoTicketNumber().getLottoNumber()).isEqualTo(lottoTickets.get(1).getLottoTicketNumber().getLottoNumber()),
                () -> assertThat(lottoTicket3.getLottoTicketNumber().getLottoNumber()).isEqualTo(lottoTickets.get(2).getLottoTicketNumber().getLottoNumber())
        );
    }

    @Test
    @DisplayName("로또 우승 통계 계산 및 출력 입력값을 받기 때문에 진행 불가")
    public void generateLottoWinningStatisticsTest() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("당첨 번호 중복 번호 예외 처리 테스트")
    public  void validateDuplicationTest1() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 5);
        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> lottoService.validateDuplication(winningNumber));
    }

    @Test
    @DisplayName("당첨 번호 중복 번호 예외 처리 테스트")
    public  void validateDuplicationTest2() {
        //given
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 5;

        //when

        //then
        assertThrows(IllegalArgumentException.class, () -> lottoService.validateDuplication(bonusBall, winningNumber));
    }

    @Test
    @DisplayName("로또 서비스 작동 - 테스트 불가")
    public void runTest() {
        //given
        //when
        //then
    }
}
