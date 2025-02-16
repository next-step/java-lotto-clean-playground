package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import exception.LottoNotEnoughMoneyException;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final List<Integer> NUMBER_POOL = generateNumberPool();

    private static List<Integer> generateNumberPool() {
        return IntStream.rangeClosed(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }

    public Lottos buyLotto(int money, int manualCount, List<List<Integer>> manualNumbers) {
        validateEnoughMoney(money, manualCount);
        Lottos manualLottos = buyManualLottos(manualNumbers);
        int autoCount = money / LOTTO_PRICE - manualCount;
        Lottos autoLottos = buyAutoLottos(autoCount);
        return combineLottos(manualLottos, autoLottos);
    }

    private void validateEnoughMoney(int money, int manualCount) {
        if (money < LOTTO_PRICE) {
            throw new LottoNotEnoughMoneyException("로또 구매 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }
        if (money > LOTTO_PRICE * manualCount) {
            throw new LottoNotEnoughMoneyException("수동으로 구매할 로또");
        }
    }

    private Lottos buyManualLottos(List<List<Integer>> manualNumbers) {
        List<Lotto> lottos = manualNumbers.stream()
            .map(Lotto::new)
            .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private Lottos buyAutoLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return new Lottos(lottos);
    }

    private Lotto generateLotto() {
        List<Integer> shuffledNumbers = generateShuffleNumbers();
        return new Lotto(selectLottoNumbers(shuffledNumbers));
    }

    private List<Integer> generateShuffleNumbers() {
        List<Integer> numbers = new ArrayList<>(NUMBER_POOL);
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> selectLottoNumbers(List<Integer> numbers) {
        return numbers.subList(0, LottoNumbers.NUMBER_COUNT);
    }

    private Lottos combineLottos(Lottos manualLottos, Lottos autoLottos) {
        List<Lotto> mergedLottos = new ArrayList<>(manualLottos.getLottos());
        mergedLottos.addAll(autoLottos.getLottos());
        return new Lottos(mergedLottos);
    }
}
