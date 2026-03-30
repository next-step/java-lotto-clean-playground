package model;

import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoBatchTest {

    @Test
    void testGetMatchCountPerLottoOrderDoesntMatter() {
        //given
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(List.of(1,2,3,4,5,6)));
        lottoList.add(new Lotto(List.of(6,5,4,3,2,1)));
        List<Integer>winningNumbers = new ArrayList<>(List.of(1,7,2,8,3,9));
        LottoBatch lottoBatch = new LottoBatch(lottoList);

        //when
        List<Integer> result = lottoBatch.getMatchCountPerLotto(winningNumbers);

        Assertions.assertEquals(2, result.size());
        assertThat(result).hasSameElementsAs(List.of(3,3));
    }

    @Test
    void testGetMatchCountPerLottoCountCorrectly() {
        //given
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(List.of(1,2,3,4,5,6)));
        lottoList.add(new Lotto(List.of(2,4,6,8,10,12)));
        List<Integer>winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        LottoBatch lottoBatch = new LottoBatch(lottoList);

        //when
        List<Integer> result = lottoBatch.getMatchCountPerLotto(winningNumbers);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(List.of(6, 3), result);
        }

    @Test
    void testGetReturnRatio() {
        //given
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        lottoList.add(new Lotto(List.of(10, 11, 12, 13, 14, 15)));
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,6));
        LottoBatch lottoBatch = new LottoBatch(lottoList);

        //when
        double returnRatio = lottoBatch.getReturnRatio(winningNumbers);

        //then
        double correctRatio = (1.0 * LottoSettingsConstants.THREE_MATCH_PRICE)/(LottoSettingsConstants.LOTTO_PRICE * 2);
        Assertions.assertEquals(correctRatio, returnRatio);
    }
}