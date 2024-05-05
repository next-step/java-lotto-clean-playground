package model;

import java.util.List;
import java.util.stream.IntStream;
import view.InputView;
import view.OutputView;
import view.dto.LottoResponse;
import view.dto.LottoStatisticsResponse;

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

        WinningLotto winningLotto = readWinningLotto();

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

    private WinningLotto readWinningLotto() {
        List<LottoNumber> winningNumbers = inputView.readWinningNumbers().stream()
                                                    .map(LottoNumber::valueOf)
                                                    .toList();
        final LottoNumber bonusNumber = new LottoNumber(inputView.readBonusNumber());
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    private ResultStatistics calculateResultStatistics(List<Lotto> lottos, WinningLotto winningLotto) {
        List<Rank> ranks = lottos.stream()
                                 .map(winningLotto::match)
                                 .toList();
        return ResultStatistics.from(ranks);
    }
}
