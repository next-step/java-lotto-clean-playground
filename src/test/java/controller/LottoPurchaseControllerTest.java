package controller;

import common.NumberGenerator;
import common.TestNumberGenerator;
import constants.LottoSettingsConstants;
import controller.mock.MockInputView;
import controller.mock.MockLottoFactory;
import controller.mock.MockLottoPurchaseController;
import controller.mock.MockOutputView;
import model.LottoBatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LottoPurchaseControllerTest {
    @Test
    @DisplayName("통합 테스트: 로또 구매")
    void purchase_calls_intended_functions() {
        // given
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<>());
        List<Integer> testNumbers = new ArrayList<>();
        for (int i = 1; i <= LottoSettingsConstants.LOTTO_SIZE * 2; i++) {
            testNumbers.add(i);
        }
        NumberGenerator testNumberGenerator = new TestNumberGenerator(testNumbers);
        MockLottoFactory lottoFactory = new MockLottoFactory(testNumberGenerator);

        int testPrice = LottoSettingsConstants.LOTTO_PRICE * 2;
        Scanner scanner = new Scanner(new ByteArrayInputStream(Integer.toString(testPrice).getBytes()));
        MockInputView inputView = new MockInputView(scanner);
        MockOutputView outputView = new MockOutputView();
        MockLottoPurchaseController controller = new MockLottoPurchaseController(lottoBatch, lottoFactory, inputView, outputView);

        //when
        controller.purchase();

        // then
        Assertions.assertTrue(outputView.printPurchaseResultCalled);
        Assertions.assertTrue(inputView.getUserCashInputCalled);
        Assertions.assertEquals(2, lottoFactory.generateLottoCalledCount);
        Assertions.assertEquals(2, controller.wrapLottoIntoDtoCallCount);
        Assertions.assertTrue(controller.checkPriceHigherThanSingleLottoPriceCalled);

    }
}
