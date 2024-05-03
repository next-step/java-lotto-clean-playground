package model;

import java.util.List;
import java.util.stream.IntStream;
import view.InputView;
import view.dto.LottoResponse;
import view.dto.LottoStatisticsResponse;
import view.OutputView;

public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Lotto> lottos = publishLotto();
        printPublishedResult(lottos);
        Lotto winningLotto = readWinningLotto();
        ResultStatistics resultStatistics = calculateResultStatistics(lottos, winningLotto);
        LottoStatisticsResponse response = LottoStatisticsResponse.from(resultStatistics);
        outputView.printResultStatistics(response);
    }

    private List<Lotto> publishLotto() {
        LottoPrice price = LottoPrice.valueOf(inputView.readLottoPrice());
        int lottoAmount = price.divideByUnit();
        return IntStream.range(0, lottoAmount)
                .mapToObj(i -> RandomLottoGenerator.generateLotto())
                .toList();
    }

    private void printPublishedResult(List<Lotto> lottos) {
        List<LottoResponse> results = lottos.stream().map(LottoResponse::from).toList();
        outputView.printLotto(results);
    }

    private Lotto readWinningLotto() {
        List<LottoNumber> winningNumbers = inputView.readWinningNumbers().stream()
                .map(LottoNumber::valueOf)
                .toList();
        return new Lotto(winningNumbers);
    }

    private ResultStatistics calculateResultStatistics(List<Lotto> lottos, Lotto winningLotto) {
        List<Rank> ranks = lottos.stream()
                .map(lotto -> lotto.matchRank(winningLotto))
                .toList();
        return ResultStatistics.from(ranks);
    }
}
