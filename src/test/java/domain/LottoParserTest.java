package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoParserTest {

    @Nested
    @DisplayName("LottoParser 검증 테스트")
    class LottoParserValidateTest{

        @ParameterizedTest
        @ValueSource(strings = {" ",""})
        void validateEmpty_입력값이없을때_예외처리(String input) {
            assertThatThrownBy(() -> new WinningLotto(LottoParser.parse(input),new LottoNumber(30)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("번호를 입력해야 합니다.");
        }

        @Test
        void validateEmpty_입력값이null일때_예외처리() {

            String input = null;
            assertThatThrownBy(() -> new WinningLotto(LottoParser.parse(input),new LottoNumber(30)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("번호를 입력해야 합니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4,5,당첨", "ㄱ,ㄴ,ㄷ,ㄹ,ㅁ,ㅂ","1,2,3,,5,6"})
        void parse_입력값에숫자아닌값이포함되어있을때_예외처리(String input) {
            assertThatThrownBy(() -> new WinningLotto(LottoParser.parse(input),new LottoNumber(30)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("숫자만 입력할 수 있습니다.");
        }
    }

    @Nested
    @DisplayName("LottoParser parse 정상적 진행되는지 테스트")
    class LottoParserParseTest{

        @Nested
        @DisplayName("입력값에 띄어쓰기 없는 경우")
        class LottoParsertextWithoutSpaces {

            @Test
            void parse_String입력값을구분자로나눠_Lotto객체를반환한다() {
                String input = "1,2,3,4,5,6";
                Lotto lotto = LottoParser.parse(input);

                List<Integer> actualNumbers = lotto.getNumbers().stream()
                        .map(LottoNumber::getLottoNumber)
                        .collect(Collectors.toList());

                Assertions.assertThat(actualNumbers).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
            }
        }

        @Nested
        @DisplayName("입력값에 띄어쓰기 있는 경우")
        class LottoParsertextHasSpaces {

            @Test
            void parse_공백제거된String입력값을구분자로나눠_Lotto객체를반환한다() {
                String input = "1,   2, 3 ,4,5, 6";
                Lotto lotto = LottoParser.parse(input);

                List<Integer> actualNumbers = lotto.getNumbers().stream()
                        .map(LottoNumber::getLottoNumber)
                        .collect(Collectors.toList());

                Assertions.assertThat(actualNumbers).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
            }
        }
    }
}
