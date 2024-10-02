package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTicketTest {
    @Test
    @DisplayName("로또 티켓 생성 시 6개 번호가 아닌 경우 예외가 발생헌다.")
    void generateLottoTicketWithInvalidSize(){
        List<LottoNumber> invalidNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
        );

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()-> new LottoTicket(invalidNumbers))
                .withMessage("로또 티켓은 6개의 번호로 구성되어야 합니다.");
    }

    @Test
    @DisplayName("로또 티켓 생성 시 중복 번호가 있는 경우 예외가 발생한다.")
    void generateLottoTicketWithDuplicateNumbers(){
        List<LottoNumber> invalidDuplicateNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(3), new LottoNumber(4), new LottoNumber(5)
        );

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->new LottoTicket(invalidDuplicateNumbers))
                .withMessage("로또 번호에는 중복이 존재하지 않아야 합니다.");
    }

    @Test
    @DisplayName("현재 번호가 로또 티켓에 포함됐는지 여부를 확인한다.")
    void testLottoTicketContainsNumber(){
        List <LottoNumber> validNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        LottoTicket lottoTicket = new LottoTicket(validNumbers);

        assertThat(lottoTicket.contains(new LottoNumber(1))).isTrue();
        assertThat(lottoTicket.contains(new LottoNumber(7))).isFalse();
    }

    @Test
    @DisplayName("로또 당첨 번호와 현재 티켓 번호의 일치하는 개수가 맞는지 확인한다.")
    void testCountMatchingNumbers(){
        LottoTicket winningNumbers = new LottoTicket(Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));

        LottoTicket ticket1 = new LottoTicket(Arrays.asList(
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6),
                new LottoNumber(7), new LottoNumber(8), new LottoNumber(9)
        ));

        int matchingCount = ticket1.countMatchingNumbers(winningNumbers);
        assertThat(matchingCount).isEqualTo(3);
    }

    @Test
    @DisplayName("문자열에서 로또 티켓으로 변환한다.")
    void testTransformStringToLottoTicket(){
        String input = "1, 2, 3, 4, 5, 6";
        LottoTicket lottoTicket = LottoTicket.transformStringToLottoTicket(input);

        assertThat(lottoTicket.getNumbers()).containsExactly(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
    }

    @Test
    @DisplayName("toString 메서드 테스트")
    void testToStringTicket(){
        List<LottoNumber> validNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        LottoTicket lottoTicket = new LottoTicket(validNumbers);

        assertThat(lottoTicket.toString()).isEqualTo("1, 2, 3, 4, 5, 6");
    }
}
