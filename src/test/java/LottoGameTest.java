import domain.enums.LotteryPrize;
import domain.lotto.Lotto;
import domain.lotto.LottoSeller;
import domain.lotto.WinningLotto;
import domain.lotto.collection.LottoTickets;
import domain.lotto.collection.WinningStatistics;
import domain.lotto.wrap.LottoNumber;
import domain.lotto.wrap.Money;
import fixed.FixedDrawLottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    private InputView inputViewOf(String input) {
        return new InputView(new ByteArrayInputStream(input.getBytes()));
    }

    private static List<LottoNumber> toLottoNumbers(int... values) {
        return Arrays.stream(values)
                .mapToObj(LottoNumber::new)
                .toList();
    }

    @Nested
    @DisplayName("구입 금액 입력 테스트")
    class PaymentInputTest {

        @Test
        @DisplayName("문자열을 입력한 경우 재입력 요청")
        void ifStringInput() {
            // given
            InputView inputView = inputViewOf("만원\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::payment
            );
        }

        @Test
        @DisplayName("음의 정수를 입력한 경우 재입력 요청")
        void ifNegativeInput() {
            // given
            InputView inputView = inputViewOf("-10000\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::payment
            );
        }

        @Test
        @DisplayName("실수를 입력한 경우 재입력 요청")
        void ifFloatInput() {
            // given
            InputView inputView = inputViewOf("10000.5\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::payment
            );
        }

        @Test
        @DisplayName("구입 금액이 정상적으로 처리된 경우")
        void validPayment() {
            // given
            InputView inputView = inputViewOf("10000\n");

            // when
            Money payment = inputView.payment();

            // then
            assertThat(payment.getAmount()).isEqualTo(10000);
        }

        @Test
        @DisplayName("1000원 단위가 아닌 금액은 잔돈으로 반환")
        void changeIsReturned() {
            // given
            LottoSeller seller = new LottoSeller(
                    new Money(10_500), List.of(), new FixedDrawLottoNumber(1, 2, 3, 4, 5, 6));

            // then
            assertThat(seller.getChange()).isEqualTo(500);
            assertThat(seller.getPaid().getAmount()).isEqualTo(10_000);
        }

        @Test
        @DisplayName("구입 금액만큼 로또가 발급")
        void ticketCountMatchesPayment() {
            // given
            LottoSeller seller = new LottoSeller(
                    new Money(10_000), List.of(), new FixedDrawLottoNumber(1, 2, 3, 4, 5, 6));

            // then
            assertThat(seller.getAmount()).isEqualTo(10);
            assertThat(seller.getTickets().getTickets()).hasSize(10);
        }
    }

    @Nested
    @DisplayName("로또 번호 테스트")
    class LottoDomainTest {

        @Test
        @DisplayName("로또 번호가 7개면 예외 발생")
        void ifSevenNumbers() {
            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6, 7))
            );
        }

        @Test
        @DisplayName("로또 번호가 5개면 예외 발생")
        void ifFiveNumbers() {
            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new Lotto(toLottoNumbers(1, 2, 3, 4, 5))
            );
        }

        @Test
        @DisplayName("로또 번호가 중복되면 예외 발생")
        void ifDuplicateNumbers() {
            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new Lotto(toLottoNumbers(1, 1, 2, 3, 4, 5))
            );
        }

        @Test
        @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외 발생")
        void ifOutOfRange() {
            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new LottoNumber(46)
            );
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new LottoNumber(0)
            );
        }
    }

    @Nested
    @DisplayName("지난 주 당첨 번호 입력 테스트")
    class WinningNumberInputTest {

        @Test
        @DisplayName("5개만 입력한 경우 재입력 요청")
        void ifFiveNumbersInput() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::lastWeekWinningNumbers
            );
        }

        @Test
        @DisplayName("7개를 입력한 경우 재입력 요청")
        void ifSevenNumbersInput() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5,6,7\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::lastWeekWinningNumbers
            );
        }

        @Test
        @DisplayName("중복된 번호를 입력한 경우 재입력 요청")
        void ifDuplicateNumbersInput() {
            // given
            InputView inputView = inputViewOf("1,1,2,3,4,5\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::lastWeekWinningNumbers
            );
        }

        @Test
        @DisplayName("콤마 없이 입력한 경우 재입력 요청")
        void ifNoCommaInput() {
            // given
            InputView inputView = inputViewOf("123456\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::lastWeekWinningNumbers
            );
        }

        @Test
        @DisplayName("입력한 당첨 번호가 그대로 저장된다")
        void savedExactly() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5,6\n");

            // when
            Lotto lotto = inputView.lastWeekWinningNumbers();

            // then
            assertThat(toLottoNumbers(1, 2, 3, 4, 5, 6)).allMatch(lotto::contains);
            assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
        }
    }

    @Nested
    @DisplayName("수동 구매 수 입력 테스트")
    class ManualCountInputTest {

        @Test
        @DisplayName("문자열을 입력한 경우 재입력 요청")
        void ifStringInput() {
            // given
            InputView inputView = inputViewOf("셋\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::manualCount
            );
        }

        @Test
        @DisplayName("음의 정수를 입력한 경우 재입력 요청")
        void ifNegativeInput() {
            // given
            InputView inputView = inputViewOf("-3\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::manualCount
            );
        }

        @Test
        @DisplayName("실수를 입력한 경우 재입력 요청")
        void ifFloatInput() {
            // given
            InputView inputView = inputViewOf("3.5\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::manualCount
            );
        }

        @Test
        @DisplayName("수동 구매 수가 정상적으로 처리된 경우")
        void validManualCount() {
            // given
            InputView inputView = inputViewOf("3\n");

            // when
            int count = inputView.manualCount();

            // then
            assertThat(count).isEqualTo(3);
        }

        @Test
        @DisplayName("0을 입력하면 전부 자동으로 구매한다")
        void ifZeroInput() {
            // given
            InputView inputView = inputViewOf("0\n");

            // when
            int count = inputView.manualCount();

            // then
            assertThat(count).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("수동 구매 번호 입력 테스트")
    class ManualLottoInputTest {

        @Test
        @DisplayName("5개만 입력한 경우 재입력 요청")
        void ifFiveNumbersInput() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::manualLottoNumbers
            );
        }

        @Test
        @DisplayName("7개를 입력한 경우 재입력 요청")
        void ifSevenNumbersInput() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5,6,7\n");

            // then
            Assertions.assertThrows(
                    NoSuchElementException.class,
                    // when
                    inputView::manualLottoNumbers
            );
        }

        @Test
        @DisplayName("수동 번호 입력 검증")
        void savedExactly() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5,6\n");

            // when
            Lotto lotto = inputView.manualLottoNumbers();

            // then
            assertThat(toLottoNumbers(1, 2, 3, 4, 5, 6)).allMatch(lotto::contains);
            assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
        }

        @Test
        @DisplayName("3회 입력하면 수동 3장이 순서대로 저장되고 나머지는 자동")
        void threeManualLottos() {
            // given
            InputView inputView = inputViewOf("1,2,3,4,5,6\n7,8,9,10,11,12\n13,14,15,16,17,18\n");
            List<Lotto> manualLottos = List.of(
                    inputView.manualLottoNumbers(),
                    inputView.manualLottoNumbers(),
                    inputView.manualLottoNumbers()
            );

            // when
            LottoSeller seller = new LottoSeller(
                    new Money(10_000), manualLottos, new FixedDrawLottoNumber(40, 41, 42, 43, 44, 45));

            // then
            assertThat(seller.getManualCount()).isEqualTo(3);
            assertThat(seller.getAutoCount()).isEqualTo(7);

            List<Lotto> tickets = seller.getTickets().getTickets();
            assertThat(tickets).hasSize(10);
            assertThat(tickets.get(0).toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
            assertThat(tickets.get(1).toString()).isEqualTo("[7, 8, 9, 10, 11, 12]");
            assertThat(tickets.get(2).toString()).isEqualTo("[13, 14, 15, 16, 17, 18]");
        }

        @Test
        @DisplayName("수동 구매 수가 구입 금액으로 살 수 있는 개수를 초과하면 예외 발생")
        void ifManualCountExceedsPayment() {
            // given
            List<Lotto> manualLottos = List.of(
                    new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6)),
                    new Lotto(toLottoNumbers(7, 8, 9, 10, 11, 12)),
                    new Lotto(toLottoNumbers(13, 14, 15, 16, 17, 18))
            );

            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new LottoSeller(
                            new Money(2_000), manualLottos, new FixedDrawLottoNumber(40, 41, 42, 43, 44, 45))
            );
        }
    }

    @Nested
    @DisplayName("보너스 볼 입력 테스트")
    class BonusNumberInputTest {

        @Test
        @DisplayName("문자열을 입력한 경우 예외가 발생한다")
        void ifStringInput() {
            // given
            InputView inputView = inputViewOf("칠\n");

            // then
            Assertions.assertThrows(
                    InputMismatchException.class,
                    // when
                    inputView::bonusNumber
            );
        }

        @Test
        @DisplayName("실수를 입력한 경우 예외 발생")
        void ifFloatInput() {
            // given
            InputView inputView = inputViewOf("7.5\n");

            // then
            Assertions.assertThrows(
                    InputMismatchException.class,
                    // when
                    inputView::bonusNumber
            );
        }

        @Test
        @DisplayName("음의 정수를 입력한 경우 예외 발생")
        void ifNegativeInput() {
            // given
            InputView inputView = inputViewOf("-7\n");

            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    inputView::bonusNumber
            );
        }

        @Test
        @DisplayName("보너스 볼이 정상적으로 처리된 경우")
        void validBonusNumber() {
            // given
            InputView inputView = inputViewOf("7\n");

            // when
            LottoNumber bonus = inputView.bonusNumber();

            // then
            assertThat(bonus).isEqualTo(new LottoNumber(7));
        }

        @Test
        @DisplayName("보너스 볼이 당첨 번호와 중복되면 예외가 발생한다")
        void ifBonusDuplicatesWinningNumbers() {
            // given
            Lotto winningNumbers = new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6));

            // then
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    // when
                    () -> new WinningLotto(winningNumbers, new LottoNumber(6))
            );
        }
    }

    @Nested
    @DisplayName("당첨 통계 및 수익률 테스트")
    class WinningStatisticsTest {

        private final WinningLotto winningLotto = new WinningLotto(
                new Lotto(toLottoNumbers(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

        private final Money paid = new Money(1_000);

        private WinningStatistics statisticsOf(int... ticketNumbers) {
            LottoTickets tickets = new LottoTickets(List.of(new Lotto(toLottoNumbers(ticketNumbers))));
            return tickets.match(winningLotto);
        }

        @Test
        @DisplayName("3개 일치하면 5등, 수익률은 5.0")
        void fifthPrize() {
            // when
            WinningStatistics statistics = statisticsOf(1, 2, 3, 43, 44, 45);

            // then
            assertThat(statistics.countOf(LotteryPrize.FIFTH)).isEqualTo(1);
            assertThat(statistics.returnRate(paid)).isEqualTo(5.0);
        }

        @Test
        @DisplayName("4개 일치하면 4등, 수익률은 50.0")
        void fourthPrize() {
            // when
            WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 44, 45);

            // then
            assertThat(statistics.countOf(LotteryPrize.FOURTH)).isEqualTo(1);
            assertThat(statistics.returnRate(paid)).isEqualTo(50.0);
        }

        @Test
        @DisplayName("5개 일치하면 3등, 수익률은 1500.0")
        void thirdPrize() {
            // when
            WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 45);

            // then
            assertThat(statistics.countOf(LotteryPrize.THIRD)).isEqualTo(1);
            assertThat(statistics.countOf(LotteryPrize.SECOND)).isEqualTo(0);
            assertThat(statistics.returnRate(paid)).isEqualTo(1_500.0);
        }

        @Test
        @DisplayName("5개 일치 + 보너스 볼 일치하면 2등, 수익률은 30000.0이다")
        void secondPrize() {
            // when
            WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 7);

            // then
            assertThat(statistics.countOf(LotteryPrize.SECOND)).isEqualTo(1);
            assertThat(statistics.countOf(LotteryPrize.THIRD)).isEqualTo(0);
            assertThat(statistics.returnRate(paid)).isEqualTo(30_000.0);
        }

        @Test
        @DisplayName("6개 일치하면 1등, 수익률은 2000000.0이다")
        void firstPrize() {
            // when
            WinningStatistics statistics = statisticsOf(1, 2, 3, 4, 5, 6);

            // then
            assertThat(statistics.countOf(LotteryPrize.FIRST)).isEqualTo(1);
            assertThat(statistics.returnRate(paid)).isEqualTo(2_000_000.0);
        }

        @Test
        @DisplayName("수익률이 1 이상이면 이득, 미만이면 손해다")
        void profitOrLoss() {
            // when
            WinningStatistics win = statisticsOf(1, 2, 3, 43, 44, 45);
            WinningStatistics lose = statisticsOf(40, 41, 42, 43, 44, 45);

            // then
            assertThat(win.isProfit(paid)).isEqualTo("이득");
            assertThat(lose.isProfit(paid)).isEqualTo("손해");
        }
    }
}
