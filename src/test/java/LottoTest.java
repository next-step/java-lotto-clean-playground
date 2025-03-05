import domain.LottoNumber;
import domain.LottoResult;
import domain.LottoTicket;
import domain.Prize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoTest {
    @Nested
    class LottoNumberTest {

        @Test
        @DisplayName("유효한 로또 번호 생성 테스트")
        void createValidLottoNumber() {
            int validNumber = 25;

            LottoNumber lottoNumber = new LottoNumber(validNumber);

            assertThat(lottoNumber.getNumber()).isEqualTo(validNumber);
        }

        @Test
        @DisplayName("유효하지 않은 로또 번호 생성 시 예외 발생 테스트")
        void createInvalidLottoNumber() {
            int invalidNumberLow = 0;
            int invalidNumberHigh = 46;

            assertThatThrownBy(() -> new LottoNumber(invalidNumberLow))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1부터 45 사이여야 합니다.");

            assertThatThrownBy(() -> new LottoNumber(invalidNumberHigh))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 1부터 45 사이여야 합니다.");
        }

        @Test
        @DisplayName("LottoNumber 비교 테스트")
        void compareToTest() {
            LottoNumber number10 = new LottoNumber(10);
            LottoNumber number20 = new LottoNumber(20);

            assertThat(number10.compareTo(number20)).isLessThan(0);
            assertThat(number20.compareTo(number10)).isGreaterThan(0);
            assertThat(number10.compareTo(new LottoNumber(10))).isEqualTo(0);
        }

        @Test
        @DisplayName("LottoNumber 동일성 테스트")
        void equalsAndHashCodeTest() {
            LottoNumber number15a = new LottoNumber(15);
            LottoNumber number15b = new LottoNumber(15);
            LottoNumber number25 = new LottoNumber(25);

            assertThat(number15a).isEqualTo(number15b);
            assertThat(number15a.hashCode()).isEqualTo(number15b.hashCode());

            assertThat(number15a).isNotEqualTo(number25);
        }

        @Test
        @DisplayName("LottoNumber toString 테스트")
        void toStringTest() {
            LottoNumber number = new LottoNumber(7);
            assertThat(number.toString()).isEqualTo("7");
        }
    }

    @Nested
    class LottoTicketTest {

        @Test
        @DisplayName("유효한 로또 티켓 생성 테스트")
        void createValidLottoTicket() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            LottoTicket ticket = new LottoTicket(numbers);

            assertThat(ticket.getNumbers()).containsExactlyElementsOf(numbers);
        }

        @Test
        @DisplayName("로또 티켓에 중복된 번호가 있으면 예외 발생 테스트")
        void createLottoTicketWithDuplicateNumbers() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(23), // 중복
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            assertThatThrownBy(() -> new LottoTicket(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호는 중복될 수 없습니다.");
        }

        @Test
        @DisplayName("로또 티켓의 번호 개수가 6개가 아니면 예외 발생 테스트")
        void createLottoTicketWithInvalidNumberCount() {
            List<LottoNumber> numbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40)
            );

            assertThatThrownBy(() -> new LottoTicket(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 티켓은 6개의 번호를 가져야 합니다.");
        }

        @Test
        @DisplayName("당첨 번호와 일치하는 개수 계산 테스트")
        void countMatchingNumbersTest() {
            List<LottoNumber> ticketNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );
            LottoTicket ticket = new LottoTicket(ticketNumbers);

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(41),
                    new LottoNumber(42),
                    new LottoNumber(43)
            );

            int matchCount = ticket.countMatchingNumbers(winningNumbers);

            assertThat(matchCount).isEqualTo(3);
        }

        @Test
        @DisplayName("로또 티켓에 특정 번호가 포함되어 있는지 확인 테스트")
        void containsNumberTest() {
            List<LottoNumber> ticketNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );
            LottoTicket ticket = new LottoTicket(ticketNumbers);

            LottoNumber numberToCheck1 = new LottoNumber(23);
            LottoNumber numberToCheck2 = new LottoNumber(42);

            assertThat(ticket.containsNumber(numberToCheck1)).isTrue();
            assertThat(ticket.containsNumber(numberToCheck2)).isFalse();
        }
    }

    @Nested
    class LottoResultTest {

        @Test
        @DisplayName("수동 로또 티켓과 자동 로또 티켓을 통합하여 당첨 결과 계산 테스트")
        void testManualAndAutomaticTicketsIntegration() {
            List<LottoTicket> manualTickets = Arrays.asList(
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(8),
                            new LottoNumber(21),
                            new LottoNumber(23),
                            new LottoNumber(41),
                            new LottoNumber(42),
                            new LottoNumber(43)
                    )),
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(3),
                            new LottoNumber(5),
                            new LottoNumber(11),
                            new LottoNumber(16),
                            new LottoNumber(32),
                            new LottoNumber(38)
                    ))
            );

            List<LottoTicket> autoTickets = Arrays.asList(
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(1),
                            new LottoNumber(8),
                            new LottoNumber(11),
                            new LottoNumber(31),
                            new LottoNumber(41),
                            new LottoNumber(42)
                    )),
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(13),
                            new LottoNumber(14),
                            new LottoNumber(16),
                            new LottoNumber(38),
                            new LottoNumber(42),
                            new LottoNumber(45)
                    ))
            );

            List<LottoTicket> allTickets = Arrays.asList(
                    manualTickets.get(0),
                    manualTickets.get(1),
                    autoTickets.get(0),
                    autoTickets.get(1)
            );

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)
            );
            LottoNumber bonusNumber = new LottoNumber(7);

            LottoResult result = LottoResult.calculateLottoResult(allTickets, winningNumbers, bonusNumber);


            assertThat(result.getCount(Prize.FIRST)).isEqualTo(0);
            assertThat(result.getCount(Prize.SECOND)).isEqualTo(0);
            assertThat(result.getCount(Prize.FIFTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.FOURTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.THIRD)).isEqualTo(0);

            assertThat(result.calculateRateOfReturn(4000)).isEqualTo(0.0);
        }

        @Test
        @DisplayName("LottoResult 생성 및 수익률 계산 테스트")
        void createLottoResultAndCalculateRateOfReturn() {
            List<LottoTicket> tickets = Arrays.asList(
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(5),
                            new LottoNumber(12),
                            new LottoNumber(23),
                            new LottoNumber(34),
                            new LottoNumber(40),
                            new LottoNumber(45)
                    )),
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(5),
                            new LottoNumber(12),
                            new LottoNumber(23),
                            new LottoNumber(34),
                            new LottoNumber(40),
                            new LottoNumber(7)
                    ))
            );

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            LottoNumber bonusNumber = new LottoNumber(7);

            LottoResult result = LottoResult.calculateLottoResult(tickets, winningNumbers, bonusNumber);

            assertThat(result.getCount(Prize.FIRST)).isEqualTo(1);
            assertThat(result.getCount(Prize.SECOND)).isEqualTo(1);
            assertThat(result.getCount(Prize.FIFTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.FOURTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.THIRD)).isEqualTo(0);

            long totalPrize = Prize.FIRST.getPrizeAmount() * 1 + Prize.SECOND.getPrizeAmount() * 1;
            int purchaseAmount = 2000;
            double expectedRateOfReturn = (double) totalPrize / purchaseAmount;

            assertThat(result.calculateRateOfReturn(purchaseAmount)).isEqualTo(expectedRateOfReturn);
        }

        @Test
        @DisplayName("2등 당첨(5개 일치 + 보너스 볼 일치) 테스트")
        void calculateSecondPrize() {
            List<LottoTicket> tickets = Arrays.asList(
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(5),
                            new LottoNumber(12),
                            new LottoNumber(23),
                            new LottoNumber(34),
                            new LottoNumber(40),
                            new LottoNumber(45)
                    )),
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(5),
                            new LottoNumber(12),
                            new LottoNumber(23),
                            new LottoNumber(34),
                            new LottoNumber(40),
                            new LottoNumber(7)
                    ))
            );

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            LottoNumber bonusNumber = new LottoNumber(7);

            LottoResult result = LottoResult.calculateLottoResult(tickets, winningNumbers, bonusNumber);

            assertThat(result.getCount(Prize.FIRST)).isEqualTo(1);
            assertThat(result.getCount(Prize.SECOND)).isEqualTo(1);
            assertThat(result.getCount(Prize.FIFTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.FOURTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.THIRD)).isEqualTo(0);

            long totalPrize = Prize.FIRST.getPrizeAmount() * 1 + Prize.SECOND.getPrizeAmount() * 1;
            int purchaseAmount = 2000;
            double expectedRateOfReturn = (double) totalPrize / purchaseAmount;

            assertThat(result.calculateRateOfReturn(purchaseAmount)).isEqualTo(expectedRateOfReturn);
        }

        @Test
        @DisplayName("5개 일치 + 보너스 볼 불일치 시 5등 당첨 테스트")
        void calculateFifthPrizeWithoutBonus() {
            List<LottoTicket> tickets = Arrays.asList(
                    new LottoTicket(Arrays.asList(
                            new LottoNumber(5),
                            new LottoNumber(12),
                            new LottoNumber(23),
                            new LottoNumber(34),
                            new LottoNumber(40),
                            new LottoNumber(7)
                    ))
            );

            List<LottoNumber> winningNumbers = Arrays.asList(
                    new LottoNumber(5),
                    new LottoNumber(12),
                    new LottoNumber(23),
                    new LottoNumber(34),
                    new LottoNumber(40),
                    new LottoNumber(45)
            );

            LottoNumber bonusNumber = new LottoNumber(8);

            LottoResult result = LottoResult.calculateLottoResult(tickets, winningNumbers, bonusNumber);

            assertThat(result.getCount(Prize.FIRST)).isEqualTo(0);
            assertThat(result.getCount(Prize.SECOND)).isEqualTo(0);
            assertThat(result.getCount(Prize.FIFTH)).isEqualTo(1);
            assertThat(result.getCount(Prize.FOURTH)).isEqualTo(0);
            assertThat(result.getCount(Prize.THIRD)).isEqualTo(0);

            long totalPrize = Prize.FIFTH.getPrizeAmount() * 1;
            int purchaseAmount = 1000;
            double expectedRateOfReturn = (double) totalPrize / purchaseAmount;

            assertThat(result.calculateRateOfReturn(purchaseAmount)).isEqualTo(expectedRateOfReturn);
        }

        @Test
        @DisplayName("구매 금액이 0일 때 수익률 계산 테스트")
        void calculateRateOfReturnWithZeroPurchase() {

            LottoResult result = new LottoResult();

            int purchaseAmount = 0;

            double rateOfReturn = result.calculateRateOfReturn(purchaseAmount);

            assertThat(rateOfReturn).isEqualTo(0.0);
        }
    }
}
