package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoStoreTest {

  public static final int LOTTO_PRICE = 1000;

  private LottoStore lottoStore;
  private static final Lottos sampleLottos = new Lottos(
      List.of(Lotto.of(List.of(1, 2, 3, 4, 5, 6))));

  @BeforeEach
  void setUp() {
    lottoStore = new LottoStore();
  }

  @Test
  void 구입_금액에_맞게_로또가_발급되어야_한다() {
    Payment payment = new Payment(1000);
    Lottos lottos = lottoStore.generateLottosWithManualLottos(sampleLottos, payment);

    assertThat(lottos).hasSize(1);
  }

  @ParameterizedTest
  @ValueSource(ints = {1000, 2000, 5000, 10000})
  void 금액에_해당하는_만큼_로또_수량이_정확해야_한다(int inputPayment) {
    Payment payment = new Payment(inputPayment);
    int expectedCount = (int) payment.value() / LOTTO_PRICE;

    Lottos lottos = lottoStore.generateLottosWithManualLottos(sampleLottos, payment);

    assertThat(lottos).hasSize(expectedCount);
  }

  @Test
  void 로또_가격보다_적은_금액으로_구매하면_예외가_발생한다() {
    assertThatThrownBy(
        () -> lottoStore.generateLottosWithManualLottos(sampleLottos, new Payment(900))).isInstanceOf(
        RuntimeException.class);
  }

  @Test
  void 생성된_로또_번호는_오름차순으로_정렬되어야_한다() {
    Lottos lottos = lottoStore.generateLottosWithManualLottos(sampleLottos, new Payment(1000));
    Lotto lotto = lottos.lottos().get(0);
    List<LottoNumber> numbers = lotto.numbers();

    for (int i = 0; i < numbers.size() - 1; i++) {
      assertThat(numbers.get(i).number()).isLessThan(numbers.get(i + 1).number());
    }
  }
}