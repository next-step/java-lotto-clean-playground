import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LottoNumber 테스트")
public class LottoNumberTest {

    @Test
    @DisplayName("로또 번호 1, 45 생성")
    void generate1And45() {

        assertDoesNotThrow(() -> new LottoNumber(1));
        assertDoesNotThrow(() -> new LottoNumber(45));
    }

    @Test
    @DisplayName("로또 번호가 1보다 작으면 예외 발생")
    void exceptIfLessThan1() {

        assertThrows(IllegalArgumentException.class,
                () -> new LottoNumber(0)
        );
    }

    @Test
    @DisplayName("로또 번호가 45보다 크면 예외 발생")
    void exceptIfGreaterThan45() {

        assertThrows(IllegalArgumentException.class,
                () -> new LottoNumber(46)
        );
    }

    @Test
    @DisplayName("번호 값이 같으면 같은 로또 번호로 판단")
    void judgeSameNumberIfSameValue() {

        // 준비 & 실행
        LottoNumber number1 = new LottoNumber(10);
        LottoNumber number2 = new LottoNumber(10);

        // 검증
        assertEquals(number1, number2);
        assertEquals(number1.hashCode(), number2.hashCode());
    }

    @Test
    @DisplayName("번호 값이 다르면 다른 로또 번호로 판단")
    void judgeDifferentNumberIfDifferentValue() {

        // 준비 & 실행
        LottoNumber first = new LottoNumber(10);
        LottoNumber second = new LottoNumber(11);

        // 검증
        assertNotEquals(first, second);
    }
}
