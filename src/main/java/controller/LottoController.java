package controller;

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

        PurchaseLottosResponse purchaseLottosResponse = lottoService.purchaseLottos(amount);
        long lottoCount = purchaseLottosResponse.lottoCount();
        List<List<Integer>> allLottoNumbers = purchaseLottosResponse.lottoGroup().getAllLottoNumbers();
        lottoOutputView.printBuyLottos(lottoCount, allLottoNumbers);
    }
}
