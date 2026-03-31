package controller;

import constants.LottoSettingsConstants;
import controller.mock.MockInputView;
import controller.mock.MockLottoResultCalculatorController;
import controller.mock.MockOutputView;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LottoResultCalculatorControllerTest {
    List<Lotto> lottoList;
    LottoBatch lottoBatch;

    String testWinningNumbers;
    Scanner scanner;
    MockInputView inputView;
    MockOutputView outputView;


    @BeforeEach
    void setupTest() {
        this.lottoList = new ArrayList<>();
        lottoList.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        lottoList.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        this.lottoBatch = new LottoBatch(lottoList);

        this.testWinningNumbers = "1, 2, 3, 4, 5, 6";
        this.scanner = new Scanner(new ByteArrayInputStream(testWinningNumbers.getBytes()));
        this.inputView = new MockInputView(scanner);
        this.outputView = new MockOutputView();
    }

    @Test
    @DisplayName("결과 계산 통합 테스트")
    void calculate_calls_intended_functions() {
        // given
        LottoResultCalculatorController controller = new LottoResultCalculatorController(lottoBatch, inputView, outputView);

        // when
        controller.calculate();

        // then
        Assertions.assertTrue(inputView.getWinningNumbersCalled);
        Assertions.assertTrue(outputView.printStatsCalled);
        Assertions.assertTrue(outputView.printReturnRatioCalled);
    }

    @Test
    @DisplayName("로또별 당첨 유형 계산")
    void testGetMatchCountPerLotto(){
        //given
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        MockLottoResultCalculatorController controller = new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        //when
        List<LottoResult> matchCountPerLotto = controller.getMatchCountPerLotto(winningNumbers);

        //then
        Assertions.assertEquals(matchCountPerLotto, List.of(LottoResult.THREE, LottoResult.SIX));
    }

    @Test
    @DisplayName("로또 수익률 계산")
    void testGetReturnRate(){
        //given
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        MockLottoResultCalculatorController controller = new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        //when
        double returnRatio = controller.getReturnRatio(winningNumbers);

        // then
        double expectedRatio = ((double) LottoResult.THREE.reward + LottoResult.SIX.reward)
                / (this.lottoBatch.getLottoCount() * LottoSettingsConstants.LOTTO_PRICE);
        Assertions.assertEquals(returnRatio, expectedRatio);
    }
}
