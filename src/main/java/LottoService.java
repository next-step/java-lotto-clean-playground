import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {

    private static final int PRICE = 1000;

    private final LottoInputView inputView;
    private final LottoOutputView outputView;

    public LottoService(LottoInputView inputView, LottoOutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int money = inputView.inputMoney();
        int amount = money / PRICE;

        List<Lotto> lottos = purchaseLotto(amount);
        outputView.printPurchasedLottoCount(amount);
        outputView.printLottos(lottos);
    }

    private List<Lotto> purchaseLotto(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto(createLottoNumbers()));
        }
        return lottos;
    }

    private List<Integer> createLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        return pickFirstSixSorted(numbers);
    }

    private List<Integer> pickFirstSixSorted(List<Integer> shuffled) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            result.add(shuffled.get(i));
        }
        Collections.sort(result);
        return result;
    }
}
