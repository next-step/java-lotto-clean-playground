package controller;

import constants.LottoSettingsConstants;
import controller.mock.MockInputView;
import controller.mock.MockLottoResultCalculatorController;
import controller.mock.MockOutputView;
import dto.LottoResultDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
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
    List<Lotto> lottoList;
    LottoBatch lottoBatch;

    String testWinningNumbers;
    Scanner scanner;
    MockInputView inputView;
    MockOutputView outputView;


    @BeforeEach
    void setupTest() {
        this.lottoList = new ArrayList<>();
        this.lottoBatch = new LottoBatch();
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        this.testWinningNumbers = "1, 2, 3, 4, 5, 6";
        this.scanner = new Scanner(new ByteArrayInputStream(testWinningNumbers.getBytes()));
        this.inputView = new MockInputView(scanner);
        this.outputView = new MockOutputView();
    }

    @Test
    @DisplayName("통합 테스트: 결과 계산")
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
        List<Integer> winningNumbers = new ArrayList<>(List.of(1,2,3,4,5,6));
        MockLottoResultCalculatorController controller = new MockLottoResultCalculatorController(lottoBatch, inputView, outputView);

        //when
        double returnRatio = controller.getReturnRatio(winningNumbers);

        // then
        double expectedRatio = ((double) LottoResult.THREE.getReward()+ LottoResult.SIX.getReward())
                / (this.lottoBatch.getLottoCount() * LottoSettingsConstants.LOTTO_PRICE);
        Assertions.assertEquals(returnRatio, expectedRatio);
    }


    @Test
    @DisplayName("List<LottoResult>를 LottoResultDto 출력순으로 포장한다.")
    void testWrapLottoResultIntoDto() {
        // given
        List<LottoResult> testResult= new ArrayList<>(List.of(LottoResult.SIX, LottoResult.FIVE, LottoResult.FOUR, LottoResult.THREE));
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
