package service;

import domain.model.Lotto;
import domain.LottoGame;
import service.converter.LottoListConverter;
import service.converter.LottoResultConverter;

import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    private final LottoGame lottoGame;

    public LottoService() {
        this.lottoGame = new LottoGame();
    }

    public void buyManualLottoes(List<List<Integer>> manualLottoes) {
        List<Lotto> lottoList = manualLottoes.stream()
                .map(LottoListConverter::listToLotto)
                .collect(Collectors.toList());

        lottoGame.buyManualLottoes(lottoList);
    }

    public void buyAutoLottoes(int buyCount, int buyManualCount) {
        lottoGame.buyAutoLottoes(buyCount - buyManualCount);
    }

    public List<List<Integer>> getPurchasedLottoes() {
        return lottoGame.getPurchasedLottoes().stream()
                .map(LottoListConverter::lottoToList)
                .toList();
    }

    public int getGameTotalCount(int money) {
        return lottoGame.getGameTotalCount(money);
    }

    public Double getPrizeRate() {
        return lottoGame.calculatePrizeRate();
    }

    public List<List<Integer>> getResults(List<Integer> winningLotto, int bonusNumber) {
        return LottoResultConverter.mapToList(lottoGame.calculateResults(LottoListConverter.listToLotto(winningLotto), bonusNumber));
    }
}
