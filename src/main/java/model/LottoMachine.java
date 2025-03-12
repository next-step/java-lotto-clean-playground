package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMachine {

    private static final int LOTTO_MINIMUM_NUMBER = LottoConstants.LOTTO_MINIMUM_NUMBER.getValue();
    private static final int LOTTO_MAXIMUM_NUMBER = LottoConstants.LOTTO_MAXIMUM_NUMBER.getValue();

    public List<Lotto> purchaseTickets(int money, List<Lotto> manualNumbers) {
        int totalTickets = money / LottoConstants.LOTTO_TICKET_PRICE.getValue();
        int manualCount = manualNumbers.size();
        int autoCount = totalTickets - manualCount;

        List<Lotto> manualLottos = addManualLottos(manualNumbers);
        List<Lotto> autoLottos = generateAutoLottos(autoCount);

        return List.copyOf(mergeLottos(manualLottos, autoLottos));
    }

    private List<Integer> generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int num = LOTTO_MINIMUM_NUMBER; num <= LOTTO_MAXIMUM_NUMBER; num++) {
            numbers.add(num);
        }
        Collections.shuffle(numbers);

        return numbers.stream()
                .limit(LottoConstants.LOTTO_NUMBERS_PER_TICKET.getValue())
                .toList();
    }

    private List<Lotto> addManualLottos(List<Lotto> manualNumbers) {
        return new ArrayList<>(manualNumbers);
    }

    private List<Lotto> generateAutoLottos(int count) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            autoLottos.add(new Lotto(generateLottoNumbers()));
        }
        return List.copyOf(autoLottos);
    }

    private List<Lotto> mergeLottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        List<Lotto> allLottos = new ArrayList<>();
        allLottos.addAll(manualLottos);
        allLottos.addAll(autoLottos);
        return List.copyOf(allLottos);
    }
}
