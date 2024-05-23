package exceptionTest;

import static org.assertj.core.api.Assertions.*;

import exception.BonusBall;
import exception.CollectNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusBallTest {

    CollectNumber collectNumber = CollectNumber.from("1, 2, 3, 4, 5, 6");

    @ParameterizedTest
    @ValueSource(strings = {"7", "10", "45"})
    void BonusBall은_올바른_값이_입력되면_생성된다(String bonusNumber) {

        assertThatCode(() -> {
            BonusBall.from(bonusNumber, collectNumber);
        }).doesNotThrowAnyException();

    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "hello"})
    void 숫자가_아니면_오류를_발생한다(String bonusBall) {

        assertThatThrownBy(() -> BonusBall.from(bonusBall, collectNumber))
                .isInstanceOf(NumberFormatException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "46"})
    void 숫자가_로또범위에_없으면_오류를_발생한다(String bonusBall) {

        assertThatThrownBy(() -> BonusBall.from(bonusBall, collectNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스숫자가_로또_당첨번호와_같으면_오류가_발생한다() {

        String bonusBall = "1";

        assertThatThrownBy(() -> BonusBall.from(bonusBall, collectNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
