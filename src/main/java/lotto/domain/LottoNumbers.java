package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    public static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumberList;

    public LottoNumbers(List<Integer> integerList) {
        validate(integerList);
        this.lottoNumberList = convertToLottoNumbers(integerList);
    }

    private void validate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }

        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 한다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> integerList) {
        List<LottoNumber> numbers = new ArrayList<>();

        for (Integer value : integerList) {
            numbers.add(new LottoNumber(value));
        }

        return numbers;
    }

    public List<Integer> getNumberValues() {
        return lottoNumberList.stream()
                .map(LottoNumber::number)
                .toList();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumberList);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumberList.contains(number);
    }
}
