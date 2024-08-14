package domain.lotto;

import domain.common.ExceptionMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static domain.util.LottoUtil.createLottoTicket;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LottoTicketTest {

    @Test
    @DisplayName("생성자 테스트1 - 정상적인 숫자들로 6개 들어왔을 때 통과")
    void 생성자_테스트1() {
        List<LottoNumber> numbers = Arrays.asList(
            new LottoNumber(1), 
            new LottoNumber(2), 
            new LottoNumber(3), 
            new LottoNumber(4), 
            new LottoNumber(5), 
            new LottoNumber(6)
        );
        
        assertDoesNotThrow(() -> new LottoTicket(numbers));
    }
    
    @ParameterizedTest
    @MethodSource("test")
    @DisplayName("생성자 테스트2 - 6개 미만, 초과로 들어왔을 때 예외 발생")
    void 생성자_테스트2(List<LottoNumber> numbers) {
        Assertions.assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_NUMBERS_INVALID_SIZE);
    }
    
    private static Stream<Arguments> test() {
        return Stream.of(
                Arguments.arguments(
                    Arrays.asList(
                        new LottoNumber(1), 
                        new LottoNumber(2), 
                        new LottoNumber(3)
                    )
                ),
                Arguments.arguments(
                    Arrays.asList(
                        new LottoNumber(1), 
                        new LottoNumber(2), 
                        new LottoNumber(3), 
                        new LottoNumber(4), 
                        new LottoNumber(5), 
                        new LottoNumber(6), 
                        new LottoNumber(7)
                    )
                )
        );
    }

    @Test
    @DisplayName("생성자 테스트3 - 중복된 값 들어온 경우 예외 발생")
    void 생성자_테스트3() {
        List<LottoNumber> numbers = Arrays.asList(
            new LottoNumber(1), 
            new LottoNumber(1), 
            new LottoNumber(2), 
            new LottoNumber(2), 
            new LottoNumber(3), 
            new LottoNumber(3)
        );
        Assertions.assertThatThrownBy(() -> new LottoTicket(numbers))
                .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.DUPLICATE_VALUE);
    }

    @ParameterizedTest
    @MethodSource("mathCount")
    void 포함_개수_확인(LottoTicket winningLottoTicket, LottoTicket myTicket, int match) {
        assertThat(myTicket.getCorrectCount(winningLottoTicket)).isEqualTo(match);
    }

    private static Stream<Arguments> mathCount() {
        return Stream.of(
            Arguments.arguments(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 5, 6),
                6
            ),
            Arguments.arguments(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 5, 45),
                5
            ),
            Arguments.arguments(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 4, 44, 45),
                4
            ),
            Arguments.arguments(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 3, 43, 44, 45),
                3
            ),
            Arguments.arguments(
                createLottoTicket(1, 2, 3, 4, 5, 6),
                createLottoTicket(1, 2, 42, 43, 44, 45),
                2
            )
        );
    }
}
