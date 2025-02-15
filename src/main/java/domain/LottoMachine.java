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
        return IntStream.rangeClosed(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());
    }

    public List<Lotto> buyLotto(int money) {
        validateMoney(money);
        int lottoCount = money / LOTTO_PRICE;
        return generateLottos(lottoCount);
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE)
            throw new LottoNotEnoughMoneyException(String.format("로또를 구매하려면 최소 %d원이 필요합니다.", LOTTO_PRICE));
    }


    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
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
        return numbers.subList(0, Lotto.NUMBER_COUNT);
    }
}
