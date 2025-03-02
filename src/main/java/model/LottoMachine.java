package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;

    private List<Integer> generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int num = LOTTO_MINIMUM_NUMBER; num <= LOTTO_MAXIMUM_NUMBER; num++) {
            numbers.add(num);
        }
        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .toList();
    }

    public List<Lotto> purchaseTickets(int money, List<Lotto> manualNumbers) {
        int totalTickets = money / LOTTO_PRICE;
        int manualCount = manualNumbers.size();
        int autoCount = totalTickets - manualCount;

        List<Lotto> manualLottos = addManualLottos(manualNumbers);
        List<Lotto> autoLottos = generateAutoLottos(autoCount);

        return List.copyOf(mergeLottos(manualLottos, autoLottos));
    }

    private List<Lotto> addManualLottos(List<Lotto> manualNumbers) {
        return new ArrayList<>(manualNumbers);
    }

    private List<Lotto> generateAutoLottos(int count) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            autoLottos.add(new Lotto(generateLottoNumbers()));
        }
        return autoLottos;
    }

    private List<Lotto> mergeLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);
        return allLottos;
    }
}
