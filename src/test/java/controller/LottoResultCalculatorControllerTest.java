package controller;

import constants.LottoSettingsConstants;
import controller.mock.MockInputView;
import controller.mock.MockLottoResultCalculatorController;
import controller.mock.MockOutputView;
import dto.LottoResultDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
import model.WinCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class LottoResultCalculatorControllerTest {
    LottoBatch lottoBatch;

    String testWinningNumbersAndBonusNumber;
    Scanner scanner;
    MockInputView inputView;
    MockOutputView outputView;


    @BeforeEach
    void setupTest() {
        this.lottoBatch = new LottoBatch();
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        this.testWinningNumbersAndBonusNumber = "1, 2, 3, 4, 5, 6\n7";
        this.scanner = new Scanner(new ByteArrayInputStream(testWinningNumbersAndBonusNumber.getBytes()));
        this.inputView = new MockInputView(scanner);
        this.outputView = new MockOutputView();
    }

    @Test
    @DisplayName("결과 계산 컨트롤러")
    void calculate_calls_intended_functions() {
        // given
        MockLottoResultCalculatorController controller= new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        // when
        controller.calculate();

        // then
        Assertions.assertTrue(inputView.getWinningNumbersCalled);
        Assertions.assertTrue(outputView.printStatsCalled);
        Assertions.assertTrue(outputView.printReturnRatioCalled);
        Assertions.assertTrue(controller.wrapLottoIntoDtoCalled);
        Assertions.assertEquals(List.of(LottoResult.THREE, LottoResult.SIX), controller.mockMatchCountPerLotto);
    }

    @Test
    @DisplayName("로또 수익률 계산")
    void testGetReturnRate(){
        //given
        lottoBatch.add(new Lotto(List.of(1,2,3,4,5,7)));
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        int bonusNumber = 7;
        WinCondition winCondition = new WinCondition(winningNumbers, bonusNumber);
        MockLottoResultCalculatorController controller = new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        //when
        double returnRatio = controller.getReturnRatio(winCondition);

        // then
        double expectedRatio = ((double) LottoResult.THREE.getReward()+ LottoResult.SIX.getReward() + LottoResult.FIVE_WITH_BONUS.getReward())
                / (this.lottoBatch.getLottoCount() * LottoSettingsConstants.LOTTO_PRICE);
        Assertions.assertEquals(returnRatio, expectedRatio);
    }


    @Test
    @DisplayName("List<LottoResult>를 LottoResultDto 출력순으로 포장한다.")
    void testWrapLottoResultIntoDto() {
        // given
        List<LottoResult> testResult= new ArrayList<>(List.of(LottoResult.SIX, LottoResult.FIVE, LottoResult.FIVE_WITH_BONUS, LottoResult.FOUR, LottoResult.THREE));
        MockLottoResultCalculatorController controller= new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        //when
        LottoResultDto resultDto = controller.wrapLottoIntoDto(testResult);

        // then
        List<LottoResult> resultDtoOrder = new LinkedList<>(resultDto.lottoResults().keySet());
        Assertions.assertEquals(LottoSettingsConstants.WINNING_LOTTO_RESULT_ASCENDING_ORDER.size(), resultDtoOrder.size());
        for (int i = 0; i < LottoSettingsConstants.WINNING_LOTTO_RESULT_ASCENDING_ORDER.size(); i++){
            Assertions.assertEquals(LottoSettingsConstants.WINNING_LOTTO_RESULT_ASCENDING_ORDER.get(i), resultDtoOrder.get(i));
            LottoResult currentKey = resultDtoOrder.get(i);
            Assertions.assertEquals(1, resultDto.lottoResults().get(currentKey));
        }
    }
}
