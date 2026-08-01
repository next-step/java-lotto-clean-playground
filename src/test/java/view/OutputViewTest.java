package view;

import static org.assertj.core.api.Assertions.assertThat;

import domain.lotto.LottoTicket;
import domain.lotto.ManualPurchaseCount;
import domain.lotto.PurchasedLottos;
import domain.lotto.BonusBall;
import domain.lotto.WinningLotto;
import domain.money.PurchaseAmount;
import domain.result.LottoStatistics;
import domain.result.LottoStatisticsCalculator;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @AfterEach
    void restoreOutput() {
        System.setOut(standardOutput);
    }

    @Test
    @DisplayName("수동 구매 수와 자동 구매 수를 출력한다")
    void printManualAndAutomaticPurchaseCount() {
        OutputView outputView = new OutputView();
        PurchasedLottos purchasedLottoTickets = new PurchasedLottos(createLottoTickets());
        System.setOut(new PrintStream(outputStream));

        outputView.printPurchasedLottoTickets(purchasedLottoTickets, ManualPurchaseCount.from(1));

        assertThat(outputStream.toString()).contains("수동으로 1장, 자동으로 1개를 구매했습니다.");
    }

    @Test
    @DisplayName("수익률이 1보다 작으면 손해 안내 문구를 출력한다")
    void printLossMessageWhenProfitRateIsLessThanOne() {
        OutputView outputView = new OutputView();
        LottoStatistics statistics = createNoPrizeStatistics();
        System.setOut(new PrintStream(outputStream));

        outputView.printLottoStatistics(statistics, PurchaseAmount.from(1_000));

        assertThat(outputStream.toString()).contains("결과적으로 손해라는 의미임");
    }

    @Test
    @DisplayName("수익률이 1보다 크면 손해 안내 문구를 출력하지 않는다")
    void doNotPrintLossMessageWhenProfitRateIsGreaterThanOne() {
        OutputView outputView = new OutputView();
        LottoStatistics statistics = createPrizeStatistics();
        System.setOut(new PrintStream(outputStream));

        outputView.printLottoStatistics(statistics, PurchaseAmount.from(1_000));

        assertThat(outputStream.toString()).contains("총 수익률은 5.00입니다.");
        assertThat(outputStream.toString()).doesNotContain("결과적으로 손해라는 의미임");
    }

    private List<LottoTicket> createLottoTickets() {
        return List.of(
                new LottoTicket(List.of(1, 2, 3, 4, 5, 6)),
                new LottoTicket(List.of(7, 8, 9, 10, 11, 12))
        );
    }

    private LottoStatistics createNoPrizeStatistics() {
        return createStatistics(new LottoTicket(List.of(7, 8, 9, 10, 11, 12)));
    }

    private LottoStatistics createPrizeStatistics() {
        return createStatistics(new LottoTicket(List.of(1, 2, 3, 10, 11, 12)));
    }

    private LottoStatistics createStatistics(LottoTicket lottoTicket) {
        PurchasedLottos lottos = new PurchasedLottos(List.of(lottoTicket));
        WinningLotto winningLotto = WinningLotto.of(
                List.of(1, 2, 3, 4, 5, 6),
                BonusBall.from(13)
        );
        return new LottoStatisticsCalculator().calculate(lottos, winningLotto);
    }
}
