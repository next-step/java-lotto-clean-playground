package model;

import common.NumberGenerator;
import common.TestNumberGenerator;
import constants.LottoSettingsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LottoFactoryTest {
    LottoFactory lottoFactory;

    @Test
    void testGenerateLotto() {
        //given tesetList = {1,1,2,3,4,5,6}
        List<Integer> testList = new ArrayList<>();
        testList.add(LottoSettingsConstants.LOTTO_MINIMUM_NUMBER);
        for (int i = LottoSettingsConstants.LOTTO_MINIMUM_NUMBER; i < LottoSettingsConstants.LOTTO_MINIMUM_NUMBER + LottoSettingsConstants.LOTTO_SIZE; i++) {
            testList.add(i);
        }

        NumberGenerator numberGenerator = new TestNumberGenerator(testList);
        lottoFactory = new LottoFactory(numberGenerator);

        //when
        Lotto testLotto = lottoFactory.generateLotto();

        //then
        Assertions.assertEquals(LottoSettingsConstants.LOTTO_SIZE, testLotto.getNumbers().size());
        Set<Integer> uniqueNumbers = new HashSet<>(testLotto.getNumbers());
        Assertions.assertEquals(uniqueNumbers.size(), testLotto.getNumbers().size());
    }
}