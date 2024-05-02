import java.util.List;
import java.util.stream.IntStream;

public class LottoGame {

    private final InputView inputView;
     private final OutputView outputView;

    public LottoGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        LottoPrice price = LottoPrice.valueOf(inputView.readLottoPrice());
        List<Lotto> lottos = publishLotto(price);
        printPublishedResult(lottos);
    }

    private List<Lotto> publishLotto(LottoPrice price) {
        int lottoAmount = price.divideByUnit();
        return IntStream.range(0, lottoAmount)
                .mapToObj(i -> RandomLottoGenerator.generateLotto())
                .toList();
    }

    private void printPublishedResult(List<Lotto> lottos) {
        List<LottoResult> results = lottos.stream().map(LottoResult::from).toList();
        outputView.printLotto(results);
    }
}
