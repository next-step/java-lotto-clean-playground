package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.Money 클래스 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoneyTest {

    @Test
    void Money_생성_시_1000원_단위가_아니면_예외가_발생한다() {
        int invalidAmount = 1500;

        assertThrows(IllegalArgumentException.class, () -> new Money(invalidAmount));
    }

    @Test
    void getTotalLottoCount_호출_시_구매한_총_로또_개수를_반환한다() {
        Money money = new Money(Money.LOTTO_PRICE * 5);

        int count = money.getTotalLottoCount();

        assertEquals(5, count);
    }

    @ParameterizedTest
    @CsvSource({
            "8000, 3, 5",
            "5000, 5, 0",
            "2000, 0, 2"
    })
    void getCountOfAutoLottos_호출_시_자동_로또_개수를_반환한다(int totalAmount, int manualCount, int expectedAutoCount) {
        Money money = new Money(totalAmount);

        int actualAutoCount = money.getCountOfAutoLottos(manualCount);

        assertThat(actualAutoCount).isEqualTo(expectedAutoCount);
    }
}
