package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_SIZE = 6;

    public static List<Lotto> createAutoLottos(final int totalPrice) {
        final int lottoCount = getLottoCount(totalPrice);
        return generateAutoLottos(lottoCount);
    }

    private static int getLottoCount(final int totalPrice) {
        return totalPrice / LOTTO_PRICE;
    }

    private static List<Lotto> generateAutoLottos(final int lottoCount) {
        List<Integer> numbers = getDefaultNumbers();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(generateRandomNumbers(numbers));
            lottos.add(lotto);
        }
        return lottos;
    }

    private static List<Integer> getDefaultNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_MAX_NUM; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    private static Numbers generateRandomNumbers(final List<Integer> numbers) {
        Collections.shuffle(numbers);
        List<Integer> values = new ArrayList<>(numbers.subList(0, LOTTO_SIZE));
        Collections.sort(values);
        return new Numbers(values);
    }

    public static List<Lotto> createManualLottos(final int totalPrice,
                                                 final List<Numbers> manualNumbers) {
        final int autoLottoCount = getLottoCount(totalPrice) - manualNumbers.size();
        if (autoLottoCount == 0) {
            return generateManualLottos(manualNumbers);
        }

        List<Lotto> autoLottos = generateAutoLottos(autoLottoCount);
        List<Lotto> manualLottos = generateManualLottos(manualNumbers);
        manualLottos.addAll(autoLottos);

        return manualLottos;
    }

    private static List<Lotto> generateManualLottos(final List<Numbers> manualNumbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (Numbers manualNumber : manualNumbers) {
            lottos.add(new Lotto(manualNumber));
        }
        return lottos;
    }

}
