package controller;

import domain.LottoGroup;
import domain.WinLotto;
import dto.CalculateEarningRateResponse;
import dto.GetLottoCountResponse;
import dto.PlayLottoGameResponse;
import dto.PurchaseLottosResponse;
import service.LottoService;
import view.LottoOutputView;
import view.UserInputView;

import java.util.List;

public class LottoController {

    private final LottoOutputView lottoOutputView;
    private final LottoService lottoService;

    public LottoController(LottoOutputView lottoOutputView, LottoService lottoService) {
        this.lottoOutputView = lottoOutputView;
        this.lottoService = lottoService;
    }

    public void start(){
        lottoOutputView.printRequestAmount();
        long amount = UserInputView.readLongInput();

        GetLottoCountResponse getLottoCountResponse = lottoService.getLottoCount(amount);
        PurchaseLottosResponse purchaseLottosResponse = lottoService.purchaseLottos(getLottoCountResponse.lottoCount());
        LottoGroup userLottoGroup = purchaseLottosResponse.lottoGroup();
        List<List<Integer>> allLottoNumbers = userLottoGroup.getAllLottoNumbers();
        lottoOutputView.printBuyLottos(getLottoCountResponse.lottoCount(), allLottoNumbers);

        lottoOutputView.printRequestLastWeekWinLottoNumbers();
        String lastWeekWinLottoNumbers = UserInputView.readStringInput();
        PlayLottoGameResponse playLottoGameResponse = lottoService.playLottoGame(userLottoGroup, WinLotto.createWinLotto(lastWeekWinLottoNumbers));
        lottoOutputView.printLottoResult(playLottoGameResponse.lottoRankResultDTOS());
        CalculateEarningRateResponse calculateEarningRateResponse = lottoService.calculateEarningsRate(getLottoCountResponse.lottoCount(), playLottoGameResponse.lottoRankResultDTOS());
        lottoOutputView.printEarningsRate(calculateEarningRateResponse.earningRate());
    }
}
