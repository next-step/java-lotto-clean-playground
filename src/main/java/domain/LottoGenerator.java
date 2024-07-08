package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {

    public Lotto generateAutoLotto(List<Integer> randomNumber) {
        Collections.sort(randomNumber);
        List<LottoNumber> autoNumber = randomNumber.stream()
            .map(LottoNumber::new)
            .toList();
        return new Lotto(autoNumber);
    }

    public Lotto generateManualLotto(String input) {
        List<LottoNumber> manualNumber = convertNumberList(input);
        verifyDuplicationNumber(manualNumber);
        verifyCountNumber(manualNumber);
        return new Lotto(manualNumber);
    }

    public List<LottoNumber> convertNumberList(String input) {
        return Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    public void verifyCountNumber(List<LottoNumber> manualNumber) {
        if (manualNumber.size() != 6) {
            throw new IllegalArgumentException("번호의 개수가 올바르지 않습니다.");
        }
    }

    public void verifyDuplicationNumber(List<LottoNumber> manualNumber) {
        if (manualNumber.size() != manualNumber.stream().distinct().count()) {
            throw new IllegalArgumentException("중복되는 번호가 있습니다.");
        }
    }
}
