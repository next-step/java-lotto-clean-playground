package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.Money 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoneyTest {

    @Test
    void Money_생성_시_1000원_단위가_아니면_예외가_발생한다() {
        //given
        int invalidAmount = 1500;

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new Money(invalidAmount));
    }

    @Test
    void getTotalLottoCount_호출_시_구매한_총_로또_개수를_반환한다() {
        //given
        Money money = new Money(Money.LOTTO_PRICE * 5);

        //when
        int count = money.getTotalLottoCount();

        //then
        assertEquals(5, count);
    }

    @ParameterizedTest
    @CsvSource({
            "8000, 3, 5",
            "5000, 5, 0",
            "2000, 0, 2"
    })
    void getCountOfAutoLottos_호출_시_자동_로또_개수를_반환한다(int totalAmount, int manualCount, int expectedAutoCount) {
        //given
        Money money = new Money(totalAmount);

        //when
        int actualAutoCount = money.getCountOfAutoLottos(manualCount);

        //then
        assertThat(actualAutoCount).isEqualTo(expectedAutoCount);
    }

    @Test
    void validateManualLottoCount_호출_시_수동_개수가_구매_가능_개수를_초과하면_예외가_발생한다() {
        //given
        Money money = new Money(5000);
        int invalidManualCount = 6;

        //when & then
        assertThatThrownBy(() -> money.validateManualLottoCount(invalidManualCount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
