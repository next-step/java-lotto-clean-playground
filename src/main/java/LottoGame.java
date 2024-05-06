import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int inputPrice = inputView.inputLottoPrice();
        LottoPrice price = new LottoPrice(inputPrice);
        List<Lotto> lottos = getLottos(price);
        outputView.printLotto(lottos);
    }

    private List<Lotto> getLottos(LottoPrice price) {
        List<Lotto> lottos = new ArrayList<>();

        RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();

        for (int i = 0; i < price.getPrice() / 1000; i++) {
            lottos.add(new Lotto(randomLottoGenerator.generateLotto().numbers()));
        }

        return lottos;
    }
}
