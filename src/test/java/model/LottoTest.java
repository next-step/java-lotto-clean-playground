package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LottoTest {
    @Test
    void testGetMatchCountPerLottoWithTooFewNumbers() {
        //given
        List<Integer> winningNumbers = new ArrayList<>();

        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE - 1; i++ ){
            winningNumbers.add(i);
        }

        //when
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> Lotto.checkIfNumbersAreValid(winningNumbers));
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
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> Lotto.checkIfNumbersAreValid(winningNumbers));
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
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> Lotto.checkIfNumbersAreValid(winningNumbers));
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
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> Lotto.checkIfNumbersAreValid(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NO_DUPLICATES_ALLOWED, exception.getMessage());
    }
}
