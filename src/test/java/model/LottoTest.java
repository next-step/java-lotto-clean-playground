package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {
    @DisplayName("수동 로또가 0개 미만으로 구매시 예외가 발생하는지 확인한다.")
    @Test
    void manualLottoCountUnderZeroExceptionTest(){
        int manualLottoCount = -1;
        Lotto lotto = new Lotto(14000);

        assertThatIllegalArgumentException().isThrownBy(() -> {
            lotto.calcLottoNumbersCount(manualLottoCount);
        });
    }

    @DisplayName("입력된 수동 로또가 1부터 45의 범위를 벗어나는 경우를 확인한다.")
    @Test
    void manualLottoNumbersRangeTest(){
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,-1, 56, 3, 4));
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoNumber lottoNumber = new LottoNumber(numbers);
        });
    }
}