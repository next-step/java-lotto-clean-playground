package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

  @Test
  @DisplayName("구입 금액 5500원으로 티켓을 생성할 때, 5개의 티켓만 생성된다")
  void createLottosWith5500() {
    // Given
    LottoPrice purchaseMoney = new LottoPrice(5500);
    LottoNumberGenerator lottoNumberGenerator = LottoNumber.createLottoNumberGenerator();

    // When
    Lottos lottos = Lottos.from(purchaseMoney, new ArrayList<>(), 0, lottoNumberGenerator);

    // Then
    assertThat(lottos.getTicketCount()).isEqualTo(5);
    assertThat(lottos.getLottos()).hasSize(5);
  }

  @Test
  @DisplayName("구입 금액 6000원으로 티켓을 생성할 때, 6개의 티켓이 생성된다")
  void createLottosWith6000() {
    // Given
    LottoPrice purchaseMoney = new LottoPrice(6000);
    LottoNumberGenerator lottoNumberGenerator = LottoNumber.createLottoNumberGenerator();

    // When
    Lottos lottos = Lottos.from(purchaseMoney, new ArrayList<>(), 0, lottoNumberGenerator);

    // Then
    assertThat(lottos.getTicketCount()).isEqualTo(6);
    assertThat(lottos.getLottos()).hasSize(6);
  }

  @Test
  @DisplayName("수동 티켓 수가 음수일 경우 예외가 발생한다.")
  void manualTicketCountCannotNegative() {
    // Given
    LottoPrice purchaseMoney = new LottoPrice(6000);
    LottoNumberGenerator lottoNumberGenerator = LottoNumber.createLottoNumberGenerator();

    List<Lotto> manualLottos = new ArrayList<>();
    manualLottos.add(Lotto.createManual(List.of(new LottoNumber(1), new LottoNumber(2),
        new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))));

    // When & Then
    assertThatThrownBy(() -> Lottos.from(purchaseMoney, manualLottos, -1, lottoNumberGenerator))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("수동 구매 수는 음수일 수 없습니다.");
  }
}
