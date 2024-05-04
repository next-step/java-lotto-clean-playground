package domain;

import model.ConsumerMoney;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("소비자 투입 급액 테스트")
class ConsumerMoneyTest {

    @Test
    void 소비자_투입금액_생성() {

        final int inputMoney = 1000;

        assertThatCode(() -> ConsumerMoney.from(inputMoney)).doesNotThrowAnyException();
    }

    @Test
    void 투입금액이_0보다_작으면_생성에_실패한다() {

        final int inputMoney = -1;

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입금액은 음수일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 999})
    void 투입금액이_1000원보다_작으면_생성에_실패한다(int inputMoney) {

        assertThatThrownBy(() -> ConsumerMoney.from(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또의 최소금액은 1000원입니다.");
    }
}
