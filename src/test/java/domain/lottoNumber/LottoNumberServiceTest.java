package domain.lottoNumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoNumberServiceTest {
    private final LottoNumberService lottoNumberService = new LottoNumberService();
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;

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

    @Test
    @DisplayName("수동 로또 넘버 입력 - 테스트 불가")
    public void readManualNumbersTest() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("수동 로또 넘버 생성 - 테스트 불가")
    public void generateManualLottoNumber() {
        //given
        //when
        //then
    }

    @Test
    @DisplayName("수동 로또 번호 중복된 번호 확인 테스트")
    public void validateDuplicationTest() {
        //given
        List<Integer> manualNumbers = List.of(1, 2, 3, 4, 5, 5);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> lottoNumberService.validateDuplication(manualNumbers));
    }

    @Test
    @DisplayName("수동 로또 번호 범위 확인 테스트")
    public void validateRangeTest() {
        //given
        List<Integer> manualNumbers = List.of(1, 2, 3, 4, 5, 46);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> lottoNumberService.validateRange(manualNumbers));
    }
}
