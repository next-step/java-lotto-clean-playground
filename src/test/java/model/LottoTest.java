package model;

import constants.ErrorMessageConstants;
import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import common.ValidateLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTest {
    @Test
    @DisplayName("로또 번호 너무 적은직 검증")
    void testGetMatchCountPerLottoWithTooFewNumbers() {
        //given
        List<Integer> winningNumbers = new ArrayList<>();

        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE - 1; i++ ){
            winningNumbers.add(i);
        }

        //when
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> ValidateLotto.checkIfNumbersAreValid(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_TOO_LITTLE, exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호 너무 많은지 검증")
    void testGetMatchCountPerLottoWithTooManyNumbers() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE + 1; i++ ){
            winningNumbers.add(i);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> ValidateLotto.checkIfNumbersAreValid(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_TOO_MANY, exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호가 적절한 범위 내에 있는지 검증")
    void testGetMatchCountPerLottoWithNumbersOutOfRange() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();
        int lottoMinimum = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER;
        for (int i = lottoMinimum - 1; i < lottoMinimum - 1 + LottoSettingsConstants.LOTTO_SIZE; i++ ){
            winningNumbers.add(i);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> ValidateLotto.checkIfNumbersAreValid(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NUMBER_OUT_OF_RANGE, exception.getMessage());
    }

    @Test
    @DisplayName("로또 번호에 중복이 있는지 검증")
    void testGetMatchCountPerLottoWithNumberDuplicates() {
        //given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> winningNumbers = new ArrayList<>();

        for (int i = 0; i <LottoSettingsConstants.LOTTO_SIZE; i++ ){
            winningNumbers.add(LottoSettingsConstants.LOTTO_MINIMUM_NUMBER);
        }

        //when & then
        Exception exception= Assertions.assertThrows(IllegalArgumentException.class, () -> ValidateLotto.checkIfNumbersAreValid(winningNumbers));
        Assertions.assertEquals(ErrorMessageConstants.NO_DUPLICATES_ALLOWED, exception.getMessage());
    }

    @Test
    @DisplayName("로또별 당첨 유형 계산")
    void testGetLottoResult(){
        //given
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        Lotto lotto = new Lotto(List.of(1, 2, 3, 10, 11, 12));

        //when
        LottoResult lottoResult = lotto.compareWithWinningNumbers(winningNumbers);

        //then
        Assertions.assertEquals(LottoResult.THREE, lottoResult);
    }
}
