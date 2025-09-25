import model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("model.domain.Money 클래스 테스트")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoneyTest {

    @Test
    void Money_생성_시_1000원_단위가_아니면_예외가_발생한다() {
        int invalidAmount = 1500;
        assertThrows(IllegalArgumentException.class, () -> new Money(invalidAmount));
    }

    @Test
    void getCountOfLottos_호출_시_구매_가능한_로또_개수를_반환한다() {
        Money money = new Money(Money.LOTTO_PRICE*5);

        int count = money.getCountOfLottos();

        assertEquals(5, count);
    }
}
