package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import Exception.ConsumerMoney;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ConsumerMoneyTest {

    @Test
    void 투입금액이_올바르게_입력_되면_문제없이_작동한다() {

        String inputMoney = "14000";

        assertThatCode(() -> {
            ConsumerMoney.from(inputMoney);
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Hello",
            "    ",
            "asdf21"
    })
    void 투입금액은_숫자가_아니면_오류를_발생한다(String inputMoney) {

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 투입금액은_음수일_경우_오류가_발생한다() {

        String inputMoney = "-123";

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 투입금액이_로또한개의_가격보다_낮으면_오류를_발생한다() {

        String inputMoney = "500";

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(Exception.class);
    }

    @Test
    void 투입금액이_로또를_사고_남으면_오류를_발생한다() {

        String inputMoney = "10590";

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(Exception.class);
    }
}
