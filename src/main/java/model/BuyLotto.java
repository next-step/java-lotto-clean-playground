package model;

import java.util.*;
import java.util.stream.Collectors;

public class BuyLotto {
    private LottoMoney totalPrice;
    private int lottoCount;
    private int manualLottoCount;
    private List<String> manualLottoList;
    private List<Integer> numberList;

    public BuyLotto(int totalPrice, int manualLottoCount, List<String> manualLottoList) {
        numberList = new ArrayList<>();

        for (int i = Constant.MIN_LOTTO_NUMBER; i <= Constant.MAX_LOTTO_NUMBER; i++) {
            numberList.add(i);
        }
        this.manualLottoCount = manualLottoCount;
        this.manualLottoList = manualLottoList;
        this.totalPrice = new LottoMoney(String.valueOf(totalPrice));
        this.lottoCount = totalPrice / Constant.LOTTO_PRICE - manualLottoCount;
    }

    private List<Lotto> convertManualLottoList(List<String> manualLottoList) {
        return manualLottoList.stream()
                .map(this::parseLottoNumbers)
                .collect(Collectors.toList());
    }

    private Lotto parseLottoNumbers(String lotto) {
        List<LottoNumber> numbers = Arrays.stream(lotto.split(Constant.SEPARATOR))
                .map(String::trim)
                .map(s -> new LottoNumber(s))
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public List<Lotto> generateLotto() {
        List<Lotto> lottos = convertManualLottoList(manualLottoList);

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(generateRandomNumbers()));
        }

        return lottos;
    }

    private List<LottoNumber> generateRandomNumbers() {
        Collections.shuffle(numberList);
        return numberList.subList(Constant.ZERO_COUNT, Constant.LOTTO_SIZE)
                .stream()
                .map(n -> new LottoNumber(String.valueOf(n)))
                .sorted(Comparator.comparingInt(LottoNumber::getNumber)) // 정렬
                .collect(Collectors.toList());
    }
}
