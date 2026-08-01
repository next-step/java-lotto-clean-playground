package view.input;

import domain.lotto.LottoSeller;
import domain.lotto.BuyingLotto;
import domain.lotto.wrap.money.Money;
import fixed.FixedDrawLottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.util.List;
import java.util.NoSuchElementException;

import static helper.TestHelperMethod.inputViewOf;
import static helper.TestHelperMethod.priceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class BuyingLottoInputViewTest {

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
    @DisplayName("최소 금액 검증은 뷰의 책임이 아니므로 1000원 미만의 금액도 그대로 반환")
    void isLowerThen1000() {
        // given
        InputView inputView = inputViewOf("500\n");

        // when
        Money payment = inputView.payment();

        // then
        assertThat(payment.getAmount()).isEqualTo(500);
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
        LottoSeller seller = new LottoSeller(new BuyingLotto(new Money(10_500), priceOf()),
                List.of(), new FixedDrawLottoNumber(1, 2, 3, 4, 5, 6));

        // then
        assertThat(seller.getChange()).isEqualTo(500);
        assertThat(seller.getPaid().getAmount()).isEqualTo(10_000);
    }

    @Test
    @DisplayName("구입 금액만큼 자동 로또가 발급")
    void ticketCountMatchesPayment() {
        // given
        LottoSeller seller = new LottoSeller(new BuyingLotto(new Money(10_000), priceOf()),
                List.of(), new FixedDrawLottoNumber(1, 2, 3, 4, 5, 6));

        // then
        assertThat(seller.getAutoCount()).isEqualTo(10);
        assertThat(seller.getTickets().getTickets()).hasSize(10);
    }
}
