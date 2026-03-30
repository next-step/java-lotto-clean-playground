package controller;

import common.NumberGenerator;
import common.TestNumberGenerator;
import constants.LottoSettingsConstants;
import model.LottoBatch;
import model.LottoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LottoPurchaseControllerTest {
    @Test
    @DisplayName("로또 구매 통합 테스트")
    void purchase_calls_intended_functions() {
        // given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> testNumbers = new ArrayList<>();
        for (int i = 1; i <= LottoSettingsConstants.LOTTO_SIZE * 2; i++) {
            testNumbers.add(i);
        }
        NumberGenerator testNumberGenerator = new TestNumberGenerator(testNumbers);
        LottoFactory lottoFactory = new LottoFactory(testNumberGenerator);

        int testPrice = LottoSettingsConstants.LOTTO_PRICE * 2;
        Scanner scanner = new Scanner(new ByteArrayInputStream(Integer.toString(testPrice).getBytes()));
        MockInputView inputView = new MockInputView(scanner);
        MockOutputView outputView = new MockOutputView();
        MockLottoPurchaseController controller = new MockLottoPurchaseController(lottoBatch, lottoFactory, inputView, outputView);

        //when
        controller.purchase();

        // then
        Assertions.assertTrue(inputView.getUserCashInputCalled);
        Assertions.assertEquals(2, controller.getLottoCallCount);
        Assertions.assertEquals(2, controller.wrapLottoIntoDtoCallCount);
        Assertions.assertTrue(outputView.printPurchaseResultCalled);

    }
}
