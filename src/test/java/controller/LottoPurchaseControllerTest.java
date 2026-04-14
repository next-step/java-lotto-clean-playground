package controller;

import common.NumberGenerator;
import common.TestNumberGenerator;
import constants.LottoSettingsConstants;
import model.LottoBatch;
import model.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class LottoPurchaseControllerTest {
    @Test
    @DisplayName("계층 통합 테스트: 로또 구매 컨트롤러 테스트")
    void purchase_calls_intended_functions() {
        // given
        LottoBatch lottoBatch = new LottoBatch();
        List<Integer> testNumbers = new ArrayList<>();
        for (int i = 1; i <= LottoSettingsConstants.LOTTO_SIZE * 2; i++) {
            testNumbers.add(i);
        }
        NumberGenerator testNumberGenerator = new TestNumberGenerator(testNumbers);
        LottoFactory lottoFactory = new LottoFactory(testNumberGenerator);

        String testInput = "2000\n1\n1,2,3,7,8,9";
        Scanner scanner = new Scanner(new ByteArrayInputStream(testInput.getBytes()));
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        LottoPurchaseController controller = new LottoPurchaseController(lottoBatch, lottoFactory, inputView, outputView);

        //when
        controller.purchase();

        // then
        List<Integer>firstLottoNumbers = List.of(1,2,3,7,8,9);
        List<Integer>secondLottoNumbers= testNumbers.subList(0,LottoSettingsConstants.LOTTO_SIZE);

        assertThat(lottoBatch.getAllLotto().get(0).numbers()).hasSameElementsAs(firstLottoNumbers);
        assertThat(lottoBatch.getAllLotto().get(1).numbers()).hasSameElementsAs(secondLottoNumbers);
    }
}
