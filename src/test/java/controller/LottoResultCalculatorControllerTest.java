package controller;

import constants.LottoSettingsConstants;
import dto.LottoResultDto;
import model.Lotto;
import model.LottoBatch;
import model.LottoResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class LottoResultCalculatorControllerTest {
    LottoBatch lottoBatch;

    String testWinningNumbersAndBonusNumber;
    Scanner scanner;
    InputView inputView;
    OutputView outputView;


    @BeforeEach
    void setupTest() {
        this.lottoBatch = new LottoBatch();
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        lottoBatch.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        this.testWinningNumbersAndBonusNumber = "1, 2, 3, 4, 5, 6\n7";
        this.scanner = new Scanner(new ByteArrayInputStream(testWinningNumbersAndBonusNumber.getBytes()));
        this.inputView = new InputView(scanner);
        this.outputView = new OutputView();
    }

    @Test
    @DisplayName("계층 통합 테스트 결과 계산 컨트롤러")
    void calculate_calls_intended_functions() {
        // given
        LottoResultCalculatorController controller= new LottoResultCalculatorController(lottoBatch, inputView, outputView);
        // when

        // then
        Assertions.assertDoesNotThrow(controller::calculate);
    }

    @Test
    @DisplayName("List<LottoResult>를 LottoResultDto 출력순으로 포장한다.")
    void testWrapLottoResultIntoDto() {
        // given
        List<LottoResult> testResult= new ArrayList<>(List.of(LottoResult.SIX, LottoResult.FIVE, LottoResult.FIVE_WITH_BONUS, LottoResult.FOUR, LottoResult.THREE));
        LottoResultCalculatorController controller= new LottoResultCalculatorController(lottoBatch, inputView, outputView);

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
