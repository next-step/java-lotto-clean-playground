package domain;

import domain.lotto.Payment;
import domain.lotto.wrap.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helper.TestHelperMethod.priceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentTest {

    @Test
    @DisplayName("구입 금액이 로또 가격보다 낮으면 지불이 성립하지 않음")
    void ifAmountLowerThanPrice() {
        // then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                // when
                () -> new Payment(new Money(500), priceOf())
        );
    }

    @Test
    @DisplayName("구입 금액이 로또 가격과 같으면 지불이 성립")
    void ifAmountEqualsPrice() {
        // when
        Payment payment = new Payment(new Money(1_000), priceOf());

        // then
        assertThat(payment.purchasableCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("구입 금액으로 살 수 있는 로또 개수를 계산")
    void purchasableCount() {
        // when
        Payment payment = new Payment(new Money(10_000), priceOf());

        // then
        assertThat(payment.purchasableCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("1000원 단위가 아닌 금액은 잔돈으로 계산되고, 실지불액은 잔돈을 뺀 금액")
    void changeAndPaid() {
        // when
        Payment payment = new Payment(new Money(10_500), priceOf());

        // then
        assertThat(payment.change().getAmount()).isEqualTo(500);
        assertThat(payment.paid().getAmount()).isEqualTo(10_000);
        assertThat(payment.hasChange()).isTrue();
    }

    @Test
    @DisplayName("1000원 단위의 금액은 잔돈이 없음")
    void noChange() {
        // when
        Payment payment = new Payment(new Money(10_000), priceOf());

        // then
        assertThat(payment.change().getAmount()).isEqualTo(0);
        assertThat(payment.hasChange()).isFalse();
    }
}
