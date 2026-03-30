package model;

import constants.ErrorMessageConstants;
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
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        LottoBatch lottoBatch = new LottoBatch(lottoList);

        //when
        double returnRatio = lottoBatch.getReturnRatio(winningNumbers);

        //then
        double correctRatio = (double) LottoSettingsConstants.THREE_MATCH_PRICE / (LottoSettingsConstants.LOTTO_PRICE * 2);
        Assertions.assertEquals(correctRatio, returnRatio);
    }

    @Test
    void testGetMatchCountPerLottoWithTooFewNumbers() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();

        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE - 1; i++ ){
            winningNumbers.add(i);
        }

        //when
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> lottoBatch.getReturnRatio(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_TOO_LITTLE, exception.getMessage());
    }

    @Test
    void testGetMatchCountPerLottoWithTooManyNumbers() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE + 1; i++ ){
            winningNumbers.add(i);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> lottoBatch.getReturnRatio(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_TOO_MANY, exception.getMessage());
    }

    @Test
    void testGetMatchCountPerLottoWithNumbersOutOfRange() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();
        int lottoMinimum = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER;
        for (int i = lottoMinimum - 1; i < lottoMinimum - 1 + LottoSettingsConstants.LOTTO_SIZE; i++ ){
            winningNumbers.add(i);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> lottoBatch.getReturnRatio(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_OUT_OF_RANGE, exception.getMessage());
    }

    @Test
    void testGetMatchCountPerLottoWithNumberDuplicates() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();

        for (int i = 0; i <LottoSettingsConstants.LOTTO_SIZE; i++ ){
            winningNumbers.add(LottoSettingsConstants.LOTTO_MINIMUM_NUMBER);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> lottoBatch.getReturnRatio(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NO_DUPLICATES_ALLOWED, exception.getMessage());
    }
}
