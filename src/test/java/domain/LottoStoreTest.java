package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.generator.RandomLottoGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoStoreTest {

    RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

    @Nested
    @DisplayName("LottoStore 수동로또 구매 제한 예외처리")
    class LottoStoreValidateManualCountTest {
        @Test
        void validateManualCount_수동로또구매개수가총구매개수보다크면_예외처리() {

            LottoStore store = new LottoStore(randomLottoGenerator);
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
            int manualCount = 3;

            assertThatThrownBy(() -> {store.validateManualCount(purchaseAmount, manualCount);})
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("수동 로또 수는 총 구매 로또 수(1개)이하이어야 합니다.");
        }
    }

    @Nested
    @DisplayName("LottoStore buyLotto 테스트")
    class LottoStoreBuyLottoTest {

        @Test
        @DisplayName("자동로또만 구입한 경우")
        void buyLotto_입력한가격에대응되는_로또개수반환() {

            LottoStore lottoStore = new LottoStore(randomLottoGenerator);

            PurchaseAmount amount = new PurchaseAmount(4000);
            List<Lotto> manual = List.of();

            List<Lotto> result = lottoStore.buyLotto(amount, manual);

            assertThat(result).size().isEqualTo(4);
        }

        @Test
        @DisplayName("자동로또와 수동로또 혼합 구입한 경우 ")
        void buyLotto_자동로또와수동로또구매개수에대응되는_로또개수반환() {

            LottoStore lottoStore = new LottoStore(randomLottoGenerator);

            PurchaseAmount amount = new PurchaseAmount(4000);
            List<Lotto> manual = List.of(
                    new Lotto(Arrays.asList(new LottoNumber(1), new LottoNumber(2),
                            new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)))
            );

            List<Lotto> result = lottoStore.buyLotto(amount, manual);

            assertThat(result).size().isEqualTo(4);
        }
    }
}
