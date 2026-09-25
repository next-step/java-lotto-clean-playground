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
    private final NumberGenerator numberGenerator;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            NumberGenerator numberGenerator
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
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
        Lottos lottos = createLottos(purchasePrice.calculateLottoCount());
        outputView.printLottos(lottos);

        Lotto winningNumbers = toLotto(inputView.getWinningNumbers());
        LottoResult result = lottos.getMatchCount(winningNumbers);
        ResultDto resultDto = createResultDto(result, purchasePrice);
        outputView.printResult(resultDto);
    }

    private Lottos createLottos(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(numberGenerator.generate()));
        }
        return new Lottos(lottoList);
    }

    private ResultDto createResultDto(LottoResult result, PurchasePrice purchasePrice) {
        return new ResultDto(
                result.getWinningCount(Rank.THREE),
                result.getWinningCount(Rank.FOUR),
                result.getWinningCount(Rank.FIVE),
                result.getWinningCount(Rank.SIX),
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