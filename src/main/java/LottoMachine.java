import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int LOTTO_MAX_NUMBER = 45;

    public int calculateNumberOfLottos(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    public List<Lotto> generateLottos(int numberOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfLottos; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_MAX_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new Lotto(numbers.subList(0, LOTTO_NUMBER_COUNT));
    }
}
