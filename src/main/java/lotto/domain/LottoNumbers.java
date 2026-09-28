package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {
    private List<LottoNumber> lottoNumberList;

    public LottoNumbers(List<Integer> integerList) {
        validate(integerList);
        this.lottoNumberList = convertToLottoNumbers(integerList);
    }

    private void validate(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없다.");
        }

        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 한다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> integerList) {
        List<LottoNumber> numbers = new ArrayList<>();

        for (Integer value : integerList) {
            numbers.add(new LottoNumber(value));
        }

        return numbers;
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumberList);
    }

    public boolean contains(LottoNumber number) {
        return lottoNumberList.contains(number);
    }
}
