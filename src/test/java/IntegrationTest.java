import common.NumberGenerator;
import controller.LottoPurchaseController;
import controller.LottoResultCalculatorController;
import model.LottoBatch;
import model.LottoFactory;
import model.LottoNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class IntegrationTest {
    LottoBatch lottoBatch;
    NumberGenerator numberGenerator;
    LottoFactory lottoFactory;
    InputView inputView;
    OutputView outputView;

    LottoPurchaseController lottoPurchaseController;


    @BeforeEach
    void setup() {
        lottoBatch = new LottoBatch();
        numberGenerator = new LottoNumberGenerator();
        lottoFactory = new LottoFactory(numberGenerator);

        String testWinningNumbers = "2000\n1, 2, 3, 4, 5, 6";
        inputView = new InputView(new Scanner(new ByteArrayInputStream(testWinningNumbers.getBytes())));
        outputView = new OutputView();

        lottoPurchaseController = new LottoPurchaseController(
            lottoBatch, lottoFactory, inputView, outputView
        );
    }


    @Test
    @DisplayName("통합 테스트")
    void integrationTest () {
        //main
        lottoPurchaseController.purchase();
        LottoResultCalculatorController lottoResultCalculatorController = new LottoResultCalculatorController(lottoBatch, inputView, outputView);
        lottoResultCalculatorController.calculate();
    }
}
