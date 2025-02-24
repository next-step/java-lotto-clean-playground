package controller;

import domain.*;
import dto.CalculateEarningRateResponse;
import dto.LottoRankResultDTO;
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

    public void start() {
        Amount amount = requestAmount();
        long buyLottoCount = getLottoCount(amount.getValue());
        long passivityBuyCount = requestPassivityBuyCount(buyLottoCount);
        List<Lotto> passivityLottos = requestPassivityLottoNumbers(passivityBuyCount);
        LottoGroup purchaseLottoGroup = purchaseLottos(passivityLottos, buyLottoCount);
        Lotto lastWeekWinLotto = requestLastWeekWinLotto();
        LottoNumber bonusNumber = requestBonusNumber();
        List<LottoRankResultDTO> lottoRankResultDTOS = playLottoGame(purchaseLottoGroup, lastWeekWinLotto, bonusNumber);
        printEarningsRate(lottoRankResultDTOS);
    }

    private Amount requestAmount() {
        lottoOutputView.printRequestAmount();

        return new Amount(UserInputView.readLongInput());
    }

    private long getLottoCount(long amount) {
        return lottoService.getLottoCount(amount).lottoCount();
    }

    private long requestPassivityBuyCount(long buyLottoCount) {
        lottoOutputView.printRequestPassivityLottoCount();
        long passivityBuyCount = UserInputView.readLongInput();
        validateAvailableBuyCount(buyLottoCount, passivityBuyCount);

        return passivityBuyCount;
    }

    private void validateAvailableBuyCount(long buyLottoCount, long passivityBuyCount) {
        if (passivityBuyCount < 0) {
            throw new IllegalArgumentException("0 이상의 값을 입력해주세요.");
        }
        if (buyLottoCount - passivityBuyCount < 0) {
            throw new IllegalArgumentException(String.format("구매 가능한 로또 수를 초과하였습니다. 초과 개수 : %d", passivityBuyCount - buyLottoCount));
        }
    }

    private List<Lotto> requestPassivityLottoNumbers(long passivityBuyCount) {
        lottoOutputView.printRequestPassivityLottoNumbers();

        return LongStream.range(0, passivityBuyCount)
                .mapToObj(i -> Parser.parseLotto(UserInputView.readStringInput()))
                .toList();
    }

    private LottoGroup purchaseLottos(List<Lotto> passivityLottos, long buyLottoCount) {
        PurchaseLottosResponse purchaseLottosResponse = lottoService.purchaseLottos(passivityLottos, buyLottoCount - passivityLottos.size());
        LottoGroup userLottoGroup = purchaseLottosResponse.lottoGroup();
        List<List<Integer>> allLottoNumbers = userLottoGroup.getAllLottoNumbersList().stream()
                .map(
                        lottoNumbers ->
                                lottoNumbers.stream().
                                        map(LottoNumber::getNumber)
                                        .toList()
                ).toList();
        lottoOutputView.printBuyLottos(passivityLottos.size(), buyLottoCount - passivityLottos.size(), allLottoNumbers);

        return userLottoGroup;
    }

    private List<LottoRankResultDTO> playLottoGame(LottoGroup userLottoGroup, Lotto lastWeekWinLotto, LottoNumber bonusNumber) {
        PlayLottoGameResponse playLottoGameResponse = lottoService.playLottoGame(userLottoGroup, WinLotto.of(lastWeekWinLotto, bonusNumber));
        lottoOutputView.printLottoResult(playLottoGameResponse.lottoRankResultDTOS());

        return playLottoGameResponse.lottoRankResultDTOS();
    }

    private Lotto requestLastWeekWinLotto() {
        lottoOutputView.printRequestLastWeekWinLottoNumbers();
        String lastWeekWinLottoNumbers = UserInputView.readStringInput();

        return Parser.parseLotto(lastWeekWinLottoNumbers);
    }

    private LottoNumber requestBonusNumber() {
        lottoOutputView.printRequestBonusLottoNumber();

        return new LottoNumber(UserInputView.readIntInput());
    }

    private void printEarningsRate(List<LottoRankResultDTO> lottoRankResultDTOS) {
        CalculateEarningRateResponse calculateEarningRateResponse = lottoService.calculateEarningsRate(lottoRankResultDTOS);
        lottoOutputView.printEarningsRate(calculateEarningRateResponse.earningRate());
    }
}
