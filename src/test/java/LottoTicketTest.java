import model.LottoTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoTicketTest {

    @Test
    @DisplayName("복권이 오름차순으로 정렬되어있는가")
    public void getNumberUp() {
        //Given:로또 티켓 생성
        LottoTicket lottoTicket = new LottoTicket(List.of(6, 5, 4, 3, 2, 1));
        //When:로또 티켓 숫자 부여
        List<Integer> result = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> number = lottoTicket.getLottoNumbers();
        //Then:오름차순으로 정렬되어 있음.
        for (int i = 0; i < 5; i++) {
            assertThat(number.get(i)).isLessThan(number.get(i + 1));
        }
    }

    @Test
    @DisplayName("로또 넘버를 정확하게 6개 갖고 있는가")
    public void validateSize_success( ){
        //Given:1~6숫자 하나씩 갖고 있는 리스트
        List<Integer> numbers=List.of(1,2,3,4,5,6);
        //when: 로또티켓 생성
        LottoTicket lottoTicket=new LottoTicket(numbers);
        //theb: 유효 검증 테스트 하기
        Assertions.assertDoesNotThrow(()->lottoTicket.vaildateSize(numbers));
    }

    @Test
    @DisplayName("로또 넘버를 정확하게 6개 갖고 있지 않을때")
    public void validateSize_fail( ){
        //Given:1~6숫자 하나씩 갖고 있는 리스트
        List<Integer> numbers=List.of(1,2,3,4,5);
        //when: 로또티켓 생성

        //then: 유효 검증 테스트 하기
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new LottoTicket(numbers).vaildateSize(numbers);
        });
    }

    @Test
    @DisplayName("로또 범위가 1~45 사이여야 한다")
    public void validateRange_success( ){
        //Given:1~6숫자 하나씩 갖고 있는 리스트
        List<Integer> numbers=List.of(1,2,3,4,5,6);
        //when: 로또티켓 생성

        //then: 유효 검증 테스트 하기
        Assertions.assertDoesNotThrow(() -> {
            new LottoTicket(numbers).validateRange(numbers);
        });
    }

    @Test
    @DisplayName("로또 범위가 1~45 사이여야 한다-실패 ver")
    public void validateRange_fail( ){
        //Given:1~6숫자 하나씩 갖고 있는 리스트
        List<Integer> numbers=List.of(0,2,3,4,5,6);
        //when: 로또티켓 생성

        //then: 유효 검증 테스트 하기
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            new LottoTicket(numbers).validateRange(numbers);
        });
    }
}
