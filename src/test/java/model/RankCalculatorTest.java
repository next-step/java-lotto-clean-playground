package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankCalculatorTest {

    @DisplayName("한장의 로또의 등수 잘 계산되는지 확인한다.")
    @Test
    void oneLottoCorrectCountTest() {
        // given
        List<LottoNumber> lottoNumberList = new ArrayList<>(1);
        LottoNumber lottoNumber = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoNumberList.add(lottoNumber);
        Lotto lotto = new Lotto(1000, lottoNumberList);

        // when
        ArrayList<Integer> ansLottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 7, 8));
        RankCalculator rankCalculator = new RankCalculator(ansLottoNumbers, 10);
        rankCalculator.calculateAllLottoCorrectNumbers(lotto);

        // then
        assertThat(rankCalculator.getRankTypeList().get(0).getCorrectNum()).isEqualTo(4);
    }

    @DisplayName("여러 장 로또의 총 수익금과 수익률이 올바른지 확인한다.")
    @Test
    void allLottoCorrectCountTest() {
        // given
        List<LottoNumber> lottoNumberList = new ArrayList<>(5);
        LottoNumber lottoNumber1 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoNumber lottoNumber2 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 7)));
        LottoNumber lottoNumber3 = new LottoNumber(new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8)));
        LottoNumber lottoNumber4 = new LottoNumber(new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9)));
        LottoNumber lottoNumber5 = new LottoNumber(new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10)));
        lottoNumberList.add(lottoNumber1);
        lottoNumberList.add(lottoNumber2);
        lottoNumberList.add(lottoNumber3);
        lottoNumberList.add(lottoNumber4);
        lottoNumberList.add(lottoNumber5);

        // when
        Lotto lotto = new Lotto(5000, lottoNumberList);
        ArrayList<Integer> ansLottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        RankCalculator rankCalculator = new RankCalculator(ansLottoNumbers, 10);
        rankCalculator.calculateAllLottoCorrectNumbers(lotto);

        // then
        assertThat(rankCalculator.calculateRateOfReturn(lotto.getBalance())).isEqualTo(40031100);
    }

    @DisplayName("2등과 3등을 구분을 올바르게 하는지 확인한다.")
    @Test
    void secondAndThirdTest(){
        // given
        List<LottoNumber> lottoNumberList = new ArrayList<>(2);
        LottoNumber lottoNumber1 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoNumber lottoNumber2 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottoNumberList.add(lottoNumber1);
        lottoNumberList.add(lottoNumber2);

        // when
        Lotto lotto = new Lotto(2000, lottoNumberList);
        ArrayList<Integer> ansLottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 12));
        int bonusBall = 6;
        RankCalculator rankCalculator = new RankCalculator(ansLottoNumbers, bonusBall);
        rankCalculator.calculateAllLottoCorrectNumbers(lotto);

        // then
        long secondCount = rankCalculator.getRankTypeList().stream().filter(rankType -> rankType == RankType.SECOND).count();
        long thirdCount = rankCalculator.getRankTypeList().stream().filter(rankType -> rankType == RankType.THIRD).count();
        assertThat((int)secondCount).isEqualTo(1);
        assertThat((int)thirdCount).isEqualTo(1);
    }

    @DisplayName("수동 로또와 자동 로또가 동시에 작동하는지 확인한다.")
    @Test
    void manualAndAutoLottoTest(){
        // given
        List<LottoNumber> lottoNumberList = new ArrayList<>(5);
        LottoNumber lottoNumber1 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        LottoNumber lottoNumber2 = new LottoNumber(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7)));

    }
}