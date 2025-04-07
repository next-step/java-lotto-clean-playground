package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoPriceTest {

  @Test
  @DisplayName("구입 금액이 1000원 미만이면 예외가 발생한다")
  void throwExceptionWhenLessThanMinmumPurchaseMoney() {
    assertThatThrownBy(() -> new LottoPrice(500))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("구입 금액은 1000원 이상이어야 합니다.");
  }

  @Test
  @DisplayName("금액에 맞게 LottoPrice 객체가 생성된다")
  void createMoneyWithValidAmount() {
    // Given
    long validAmount = 2000;

    // When
    LottoPrice lottoPrice = new LottoPrice(validAmount);

    // Then
    assertThat(lottoPrice.getPurchaseMoney()).isEqualTo(validAmount);
  }
}
