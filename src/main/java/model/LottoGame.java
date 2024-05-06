package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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
        LottoPrice price = LottoPrice.valueOf(inputView.readLottoPrice());
        int lottoAmount = price.divideByUnit();
        final int manualLottoCount = inputView.readManualLottoCount();
        final int automaticLottoCount = lottoAmount - manualLottoCount;

        if (lottoAmount < manualLottoCount) {
            throw new IllegalArgumentException("로또 구매 금액이 부족합니다.");
        }

        List<Lotto> lottos = publishLotto(manualLottoCount, automaticLottoCount);
        printPublishedResult(lottos, manualLottoCount, automaticLottoCount);

        WinningLotto winningLotto = readWinningLotto();

        ResultStatistics resultStatistics = calculateResultStatistics(lottos, winningLotto);
        LottoStatisticsResponse response = LottoStatisticsResponse.from(resultStatistics);
        outputView.printResultStatistics(response);
    }

    private List<Lotto> publishLotto(int manualLottoCount, int automaticLottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        inputView.readManualLottoNumbers(manualLottoCount).stream()
                 .map(manualLottoNumber -> {
                     List<Integer> numbers = Stream.of(manualLottoNumber.replace(" ", "").split(","))
                                                   .map(Integer::parseInt)
                                                   .toList();
                     return Lotto.from(numbers);
                 })
                 .forEach(lottos::add);

        IntStream.range(0, automaticLottoCount)
                 .mapToObj(i -> RandomLottoGenerator.generateLotto())
                 .forEach(lottos::add);

        return lottos;
    }

    private void printPublishedResult(List<Lotto> lottos, int manualLottoCount, int automaticLottoCount) {
        List<LottoResponse> results = lottos.stream().map(LottoResponse::from).toList();
        outputView.printLotto(results, manualLottoCount, automaticLottoCount);
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
