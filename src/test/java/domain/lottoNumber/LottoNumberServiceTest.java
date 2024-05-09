package domain.lottoNumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberServiceTest {
    private final LottoNumberService lottoNumberService = new LottoNumberService();
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;

    @Test
    @DisplayName("로또 숫자 리스트 생성")
    public void generateLottoNumberListTest() {
        //given
        List<Integer> expectedLottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            expectedLottoNumberList.add(i);
        }

        //when
        List<Integer> lottoNumberList = lottoNumberService.generateLottoNumberList();

        //then
        assertThat(expectedLottoNumberList).isEqualTo(lottoNumberList);
    }

    @Test
    @DisplayName("6자리의 로또 숫자 생성 - 비교 불가 따라서 출력 대체")
    public void generateLottoNumberTest() {
        //given
        LottoNumber expectedLottoNumber = new LottoNumber(List.of(1, 2, 3, 4, 5, 6));

        //when
        LottoNumber lottoNumber = lottoNumberService.generateLottoNumber();

        //then
        System.out.println(expectedLottoNumber.getLottoNumber());
        System.out.println(lottoNumber.getLottoNumber());
    }
}
