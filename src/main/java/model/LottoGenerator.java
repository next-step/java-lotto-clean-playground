package model;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator {
    private List<Integer> numberList;

    public LottoGenerator() {
        numberList = new ArrayList<>();
        for (int i = Constant.MIN_LOTTO_NUMBER; i <= Constant.MAX_LOTTO_NUMBER; i++) {
            numberList.add(i);
        }
    }

    public List<Lotto> generateLottos(int autoLottoCount, List<String> manualLottoList) {
        List<Lotto> lottos = convertManualLottoList(manualLottoList);

        for (int i = 0; i < autoLottoCount; i++) {
            lottos.add(new Lotto(generateRandomNumbers()));
        }

        return lottos;
    }

    private List<Lotto> convertManualLottoList(List<String> manualLottoList) {
        return manualLottoList.stream()
                .map(this::parseLottoNumbers)
                .collect(Collectors.toList());
    }

    private Lotto parseLottoNumbers(String lotto) {
        List<LottoNumber> numbers = Arrays.stream(lotto.split(Constant.SEPARATOR))
                .map(String::trim)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    private List<LottoNumber> generateRandomNumbers() {
        Collections.shuffle(numberList);
        return numberList.subList(Constant.ZERO_COUNT, Constant.LOTTO_SIZE)
                .stream()
                .map(n -> new LottoNumber(String.valueOf(n)))
                .sorted(Comparator.comparingInt(LottoNumber::getNumber))
                .collect(Collectors.toList());
    }
}
