import common.LottoNumberGenerator;
import common.NumberGenerator;
import controller.LottoPurchaseController;
import controller.LottoResultCalculatorController;
import model.Lotto;
import model.LottoBatch;
import model.LottoFactory;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LottoBatch lottoBatch = new LottoBatch(new ArrayList<Lotto>());
        NumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoFactory lottoFactory = new LottoFactory(numberGenerator);
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();

        LottoPurchaseController lottoPurchaseController = new LottoPurchaseController(
            lottoBatch, lottoFactory, inputView, outputView
        );

        lottoPurchaseController.purchase();

        LottoResultCalculatorController lottoResultCalculatorController = new LottoResultCalculatorController(lottoBatch, inputView, outputView);
        lottoResultCalculatorController.calculate();
    }
}
