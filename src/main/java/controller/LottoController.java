package controller;

import domain.Lotto;
import domain.Lottos;
import domain.LottoRank;
import domain.LottoMarket;
import domain.LottoResult;
import domain.LottoNumberList;
import domain.LottoWin;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void LottoRun() {
        int price = inputView.inputPrice();
        int passiveLottoCount = inputView.inputPassiveLottoCount();

        Lottos autoLottos = generateLottos(price, passiveLottoCount);
        outputView.printLottoTickets(autoLottos.getLottos().size(), passiveLottoCount);

        List<List<Integer>> passiveLottoNumbers = inputPassiveLottoNumbers(passiveLottoCount);
        Lottos passiveLottos = generatePassiveLottos(passiveLottoNumbers);
        Lottos combinedLottos = Lottos.mergeLottos(autoLottos, passiveLottos);

        printLotto(combinedLottos);
        LottoWin(combinedLottos, price);
    }

    private Lottos generateLottos(int price, int passiveLottoCount) {
        LottoMarket lottoMarket = new LottoMarket(price, passiveLottoCount);
        return lottoMarket.generateTickets();
    }

    private List<List<Integer>> inputPassiveLottoNumbers(int passiveLottoCount) {
        List<String> passiveNumbers = inputView.inputPassiveLottoNumber(passiveLottoCount);
        List<List<Integer>> lottoNumberLists = new ArrayList<>();
        for (String passiveNumber : passiveNumbers) {
            lottoNumberLists.add(new LottoNumberList(passiveNumber).getNumbers());
        }
        return lottoNumberLists;
    }

    private Lottos generatePassiveLottos(List<List<Integer>> lottoNumberLists) {
        Lottos passiveLottos = new Lottos(new ArrayList<>());
        return passiveLottos.generatePassiveLottos(lottoNumberLists);
    }

    private void printLotto(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            outputView.printLotto(lotto.getLottoNumbers());
        }
    }

    private void LottoWin(Lottos lottos, int price) {
        String inputWinNumber = inputView.inputWinningNumber();
        int userBonusBall = inputView.inputBonusBall();
        List<Integer> winNumber= new LottoNumberList(inputWinNumber).getNumbers();

        Lotto lotto = Lotto.createLotto();
        int generateBonusNumber = lotto.getBonusNumber();

        LottoWin lottoWinning = new LottoWin();
        List<LottoRank> winCounts = lottoWinning.calculateWinCounts(lottos.getLottos(), winNumber, userBonusBall, generateBonusNumber);
        LottoResult lottoResult = new LottoResult(winCounts, price);

        outputView.printWinningResult(lottoResult.getRankResult().getRankCounts());
        outputView.printProfit(lottoResult.getProfitRate());
    }
}
