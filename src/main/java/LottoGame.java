import java.util.List;
import java.util.stream.IntStream;

public class LottoGame {

    private final InputView inputView;

    public LottoGame(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        LottoPrice price = LottoPrice.valueOf(inputView.readLottoPrice());
        List<Lotto> lottos = publishLotto(price);
    }

    private List<Lotto> publishLotto(LottoPrice price) {
        int lottoAmount = price.divideByUnit();
        return IntStream.range(0, lottoAmount)
                .mapToObj(i -> RandomLottoGenerator.generateLotto())
                .toList();
    }
}
