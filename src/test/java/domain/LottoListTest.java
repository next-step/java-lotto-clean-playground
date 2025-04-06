package domain;

import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoListTest {

    @Test
    @DisplayName("로또 금액이 유효한 값일 때, 구매한 로또 개수가 계산되어야 한다.")
    void create_LottoList_WithValidAmount() {
        long purchaseAmount = 5000;
        int manualLottoCount = 1;
        LottoGenerator manualGenerator = new ManualLottoGenerator(List.of("1,2,3,4,5,6"));
        LottoGenerator autoGenerator = new AutoLottoGenerator();
        LottoList userLottoList = LottoList.create(purchaseAmount, manualLottoCount, manualGenerator, autoGenerator);

        assertEquals(5, userLottoList.getLottoList().size()); // 총 5개여야 함
    }

    @Test
    @DisplayName("로또 구매 금액이 1000원 미만이면 예외가 발생한다.")
    void validate_PurchaseAmount_BelowMinimum_ThrowsException() {
        long purchaseAmount = 500;
        int manualLottoCount = 1;
        LottoGenerator manualGenerator = new ManualLottoGenerator(List.of("1,2,3,4,5,6"));
        LottoGenerator autoGenerator = new AutoLottoGenerator();

        assertThatThrownBy(() -> LottoList.create(purchaseAmount, manualLottoCount, manualGenerator, autoGenerator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 최소 구매 금액은 1000원입니다.");
    }

    @Test
    @DisplayName("로또 금액이 1000원 단위가 아니라면 예외가 발생한다.")
    void validate_PurchaseAmount_NotMultipleOfThousand_ThrowsException() {
        long purchaseAmount = 1500;
        int manualLottoCount = 1;
        LottoGenerator manualGenerator = new ManualLottoGenerator(List.of("1,2,3,4,5,6"));
        LottoGenerator autoGenerator = new AutoLottoGenerator();

        assertThatThrownBy(() -> LottoList.create(purchaseAmount, manualLottoCount, manualGenerator, autoGenerator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 금액은 1000원 단위여야 합니다.");
    }
}
