package controller;

import java.util.ArrayList;
import java.util.List;

import domain.Lotto;
import domain.LottoGameResult;
import domain.LottoMachine;
import domain.WinningNumbers;
import view.InputHandler;

public class LottoController {
    private final InputHandler inputHandler = new InputHandler();
    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        int purchaseAmount = inputHandler.getPurchaseAmount();

        int totalLottos = lottoMachine.calculateNumberOfLottos(purchaseAmount);

        int manualLottoCount = inputHandler.getManualLottoCount();

        List<String> manualLottoInputs = inputHandler.getManualLottoInputs(manualLottoCount);
        List<Lotto> manualLottos = createManualLottos(manualLottoInputs);

        int automaticLottoCount = totalLottos - manualLottoCount;

        List<Lotto> automaticLottos = lottoMachine.generateAutomaticLottos(automaticLottoCount);

        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(automaticLottos);

        System.out.println("수동으로 " + manualLottoCount + "장, 자동으로 " + automaticLottoCount + "개를 구매했습니다.");
        for (Lotto lotto : allLottos) {
            System.out.println(lotto);
        }

        List<Integer> winningNumbers = inputHandler.getWinningNumbers();
        int bonusNumber = inputHandler.getBonusNumber();
        WinningNumbers winNums = new WinningNumbers(winningNumbers, bonusNumber);
        System.out.println("당첨 번호: " + winNums);

        LottoGameResult gameResult = new LottoGameResult(allLottos, winNums);
        gameResult.printWinningStatistics();
    }

    private List<Lotto> createManualLottos(List<String> manualLottoInputs) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (String input : manualLottoInputs) {
            String[] inputs = input.split("[,\\s]+");
            List<Integer> numbers = new ArrayList<>();
            for (String number : inputs) {
                numbers.add(Integer.parseInt(number));
            }
            manualLottos.add(new Lotto(numbers));
        }
        return manualLottos;
    }
}
