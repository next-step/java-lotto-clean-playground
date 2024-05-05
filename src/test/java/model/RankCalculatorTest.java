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
        List<OneLotto> oneLottoList = new ArrayList<>(1);
        OneLotto oneLotto = new OneLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        oneLottoList.add(oneLotto);
        Lottos lottos = new Lottos(1000, 1000 / 1000, oneLottoList);

        ArrayList<Integer> ansLottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 7, 8)); //정답

        RankCalculator rankCalculator = new RankCalculator(new OneLotto(ansLottoNumbers));
        rankCalculator.allLottoRank(lottos);

        assertThat(rankCalculator.getCorrectNum().get(3)).isEqualTo(1);
    }

    @DisplayName("여러 장 로또의 총 수익금과 수익률이 올바른지 확인한다.")
    @Test
    void allLottoCorrectCountTest() {
        List<OneLotto> oneLottoList = new ArrayList<>(5);
        OneLotto oneLotto1 = new OneLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        OneLotto oneLotto2 = new OneLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 5, 6, 7)));
        OneLotto oneLotto3 = new OneLotto(new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8)));
        OneLotto oneLotto4 = new OneLotto(new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9)));
        OneLotto oneLotto5 = new OneLotto(new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9, 10)));
        oneLottoList.add(oneLotto1);
        oneLottoList.add(oneLotto2);
        oneLottoList.add(oneLotto3);
        oneLottoList.add(oneLotto4);
        oneLottoList.add(oneLotto5);

        Lottos lottos = new Lottos(5000, 5000 / 1000, oneLottoList);
        ArrayList<Integer> ansLottoNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        RankCalculator rankCalculator = new RankCalculator(new OneLotto(ansLottoNumbers));
        rankCalculator.allLottoRank(lottos);

        assertThat(rankCalculator.rateOfReturn(lottos.getBalance())).isEqualTo(40031100);
    }
}