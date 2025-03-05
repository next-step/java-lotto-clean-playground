package controller;

import dto.LottoNumbersDto;
import dto.LottoResultDto;
import dto.LottosDto;
import model.*;
import view.LottoInputView;
import view.LottoOutputView;

import java.text.DecimalFormat;
import java.util.List;

public class LottoController {

    private static final DecimalFormat TOTAL_PROFIT_RATE_FORMATTER = new DecimalFormat("0.00");

    private final LottoInputView lottoInputView;
    private final LottoOutputView lottoOutputView;

    private static final LottoController lottoController = new LottoController();

    private LottoController() {
        this.lottoInputView = LottoInputView.getInstance();
        this.lottoOutputView = LottoOutputView.getInstance();
    }

    public static LottoController getInstance() {
        return lottoController;
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
        List<LottoNumbers> manualLottoNumbersCollection = getManualLottoNumbers(manualLottoAmount);
        Lottos lottos = Lottos.purchase(purchaseAmount, manualLottoNumbersCollection);

        int randomLottoAmount = lottos.getLottoAmount() - manualLottoAmount;
        lottoOutputView.printLottoAmount(manualLottoAmount, randomLottoAmount);
        lottoOutputView.printLottos(new LottosDto(lottos));

        return lottos;
    }

    private List<LottoNumbers> getManualLottoNumbers(int manualLottoAmount) {
        validateManualLottoAmount(manualLottoAmount);
        if (isZero(manualLottoAmount)) {
            return List.of();
        }

        List<LottoNumbersDto> manualLottoNumbersDtos = lottoInputView.getManualLottoNumbers(manualLottoAmount);

        return manualLottoNumbersDtos.stream()
                .map(LottoNumbersDto::toLottoNumbers)
                .toList();
    }

    private void validateManualLottoAmount(int manualLottoAmount) {
        if (isNegative(manualLottoAmount)) {
            throw new IllegalArgumentException("수동으로 구매할 로또의 수는 음수일 수 없습니다: " + manualLottoAmount);
        }
    }

    private boolean isNegative(int number) {
        return number < 0;
    }

    private boolean isZero(int number) {
        return number == 0;
    }

    private LottoNumbers getWinningLottoNumbers() {
        LottoNumbersDto winningLottoNumbersDto = lottoInputView.getWinningLottoNumbers();

        return winningLottoNumbersDto.toLottoNumbers();
    }

    private LottoNumber getBonusBall() {
        int bonusBallInput = lottoInputView.getBonusBall();

        return new LottoNumber(bonusBallInput);
    }

    private void printLottoResults(LottoResults lottoResults) {
        lottoOutputView.printLottoResultHeader();

        for (LottoResult lottoResult : lottoResults.getLottoResultCollection()) {
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
