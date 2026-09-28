package controller;

import domain.*;
import dto.ResultDto;

import java.util.ArrayList;
import java.util.List;

import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoFactory lottoFactory;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            LottoFactory lottoFactory
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoFactory = lottoFactory;
    }

    public void run() {
        try {
            runApp();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private void runApp() {
        PurchasePrice purchasePrice = new PurchasePrice(inputView.getPurchasePrice());
        Lottos lottos = lottoFactory.create(purchasePrice.calculateLottoCount());
        outputView.printLottos(lottos);

        Lotto winningNumbers = toLotto(inputView.getWinningNumbers());
        LottoResult result = lottos.calculateResult(winningNumbers);
        ResultDto resultDto = createResultDto(result, purchasePrice);
        outputView.printResult(resultDto);
    }

    private ResultDto createResultDto(LottoResult result, PurchasePrice purchasePrice) {
        return new ResultDto(
                result.getWinningCount(Rank.FOURTH),
                result.getWinningCount(Rank.THIRD),
                result.getWinningCount(Rank.SECOND),
                result.getWinningCount(Rank.FIRST),
                result.calculateRateOfReturn(purchasePrice)
        );
    }

    private Lotto toLotto(String input) {
        String[] tokens = input.split(",");
        List<LottoNumber> numbers = new ArrayList<>();
        for (String token : tokens) {
            numbers.add(new LottoNumber(parseNumber(token.trim())));
        }
        return new Lotto(numbers);
    }

    private int parseNumber(String token) {
        validateNotBlank(token);

        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }

    private void validateNotBlank(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(
                    "로또 번호를 입력해야 합니다."
            );
        }
    }
}