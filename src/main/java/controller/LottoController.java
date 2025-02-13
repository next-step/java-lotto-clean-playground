package controller;

import domain.LottoGroup;
import domain.WinLotto;
import dto.CalculateEarningRateResponse;
import dto.GetLottoCountResponse;
import dto.PlayLottoGameResponse;
import dto.PurchaseLottosResponse;
import service.LottoService;
import util.Parser;
import view.LottoOutputView;
import view.UserInputView;

import java.util.List;
import java.util.stream.LongStream;

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
        lottoOutputView.printRequestPassivityLottoCount();
        long passivityBuyCount = UserInputView.readLongInput();
        if(getLottoCountResponse.lottoCount() - passivityBuyCount < 0){
            throw new IllegalArgumentException(String.format("구매 가능한 로또 수를 초과하였습니다. 초과 개수 : %d",passivityBuyCount - getLottoCountResponse.lottoCount() ));
        }
        lottoOutputView.printRequestPassivityLottoNumbers();
        List<List<Integer>> passivityLottosNumbers = LongStream.range(0, passivityBuyCount)
                .mapToObj(i -> Parser.parseIntegerList(UserInputView.readStringInput()))
                .toList();

        PurchaseLottosResponse purchaseLottosResponse = lottoService.purchaseLottos(passivityLottosNumbers, getLottoCountResponse.lottoCount() - passivityBuyCount);
        LottoGroup userLottoGroup = purchaseLottosResponse.lottoGroup();
        List<List<Integer>> allLottoNumbers = userLottoGroup.getAllLottoNumbers();
        lottoOutputView.printBuyLottos(passivityBuyCount, getLottoCountResponse.lottoCount() - passivityBuyCount, allLottoNumbers);

        lottoOutputView.printRequestLastWeekWinLottoNumbers();
        String lastWeekWinLottoNumbers = UserInputView.readStringInput();
        lottoOutputView.printRequestBonusLottoNumber();
        int bonusNumber = UserInputView.readIntInput();

        List<Integer> lastWeekWinNumbers = Parser.parseIntegerList(lastWeekWinLottoNumbers);
        PlayLottoGameResponse playLottoGameResponse = lottoService.playLottoGame(userLottoGroup, WinLotto.of(lastWeekWinNumbers, bonusNumber));
        lottoOutputView.printLottoResult(playLottoGameResponse.lottoRankResultDTOS());
        CalculateEarningRateResponse calculateEarningRateResponse = lottoService.calculateEarningsRate(getLottoCountResponse.lottoCount(), playLottoGameResponse.lottoRankResultDTOS());
        lottoOutputView.printEarningsRate(calculateEarningRateResponse.earningRate());
    }
}
