package controller;

import dto.LottoResultDto;
import dto.LottosDto;
import model.*;
import util.InputConverter;
import view.LottoInputView;
import view.LottoOutputView;

import java.text.DecimalFormat;
import java.util.List;

public class LottoController {

    private static final String TOTAL_PROFIT_FORMAT_FORM = "0.00";
    private static final DecimalFormat TOTAL_PROFIT_RATE_FORMATTER = new DecimalFormat(TOTAL_PROFIT_FORMAT_FORM);

    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;
    private final InputConverter inputConverter;

    public LottoController(
            LottoInputView lottoInputView,
            LottoOutputView lottoOutputView,
            InputConverter inputConverter
    ) {
        this.lottoInputView = lottoInputView;
        this.lottoOutputView = lottoOutputView;
        this.inputConverter = inputConverter;
    }

    public void run() {
        int purchaseAmount = lottoInputView.getPurchaseAmount();
        Lottos lottos = purchaseLottos(purchaseAmount);

        LottoNumbers winningLottoNumbers = getWinningLottoNumbers();
        LottoNumber bonusBall = getBonusBall();

        LottoResults lottoResults = new LottoResults(lottos, winningLottoNumbers, bonusBall);
        printLottoResults(lottoResults);
        printTotalProfitRate(lottoResults, purchaseAmount);
    }

    private Lottos purchaseLottos(int purchaseAmount) {
        int manualLottoAmount = lottoInputView.getManualLottoAmount();

        List<String> manualLottoNumbersInput = lottoInputView.getManualLottoNumbers(manualLottoAmount);
        List<LottoNumbers> manualLottoNumbersList = inputConverter.getLottoNumbersListFromInputs(manualLottoNumbersInput);
        Lottos lottos = Lottos.purchase(purchaseAmount, manualLottoNumbersList);

        lottoOutputView.printLottoAmount(manualLottoAmount, lottos.size() - manualLottoAmount);
        lottoOutputView.printLottos(LottosDto.from(lottos));

        return lottos;
    }

    private LottoNumbers getWinningLottoNumbers() {
        String winningLottoInput = lottoInputView.getWinningLottoString();

        return inputConverter.getLottoNumbersFromInput(winningLottoInput);
    }

    private LottoNumber getBonusBall() {
        int bonusBallInput = lottoInputView.getBonusBall();

        return new LottoNumber(bonusBallInput);
    }

    private void printLottoResults(LottoResults lottoResults) {
        lottoOutputView.printLottoResultHeader();

        for (LottoResult lottoResult : lottoResults.getLottoResultList()) {
            lottoOutputView.printLottoResult(new LottoResultDto(lottoResult));
        }
    }

    private void printTotalProfitRate(LottoResults lottoResults, int purchaseAmount) {
        double totalProfitRate = lottoResults.getTotalProfitRate(purchaseAmount);
        double formattedTotalProfitRate = formatTotalProfitRate(totalProfitRate);

        lottoOutputView.printTotalProfitRate(formattedTotalProfitRate);
    }

    private double formatTotalProfitRate(double totalProfitRate) {
        String formattedTotalProfitRate = TOTAL_PROFIT_RATE_FORMATTER.format(totalProfitRate);

        return Double.parseDouble(formattedTotalProfitRate);
    }

}
