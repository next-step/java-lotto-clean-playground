package controller;

import model.Lotto;
import model.LottoBatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LottoResultCalculatorControllerTest {
    @Test
    @DisplayName("결과 계산 통합 테스트")
    void calculate_calls_intended_functions() {
        // given
        List<Lotto> lottoList = new ArrayList<>();
        lottoList.add(new Lotto(List.of(1, 2, 3, 10, 11, 12)));
        MockLottoBatch lottoBatch = new MockLottoBatch(lottoList);

        String testWinningNumbers = "1, 2, 3, 4, 5, 6";
        Scanner scanner = new Scanner(new ByteArrayInputStream(testWinningNumbers.getBytes()));
        MockInputView inputView = new MockInputView(scanner);
        MockOutputView outputView = new MockOutputView();
        
        LottoResultCalculatorController controller = new LottoResultCalculatorController(lottoBatch, inputView, outputView);

        // when
        controller.calculate();

        // then
        Assertions.assertTrue(lottoBatch.getMatchCountPerLottoCalled);
        Assertions.assertTrue(inputView.getWinningNumbersCalled);
        Assertions.assertTrue(outputView.printStatsCalled);
        Assertions.assertTrue(outputView.printReturnRatioCalled);
    }
}
