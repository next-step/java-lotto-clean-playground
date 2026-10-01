package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < LottoNumbers.LOTTO_NUMBER_COUNT) {
            int randomNumber = (int) (Math.random() * LottoNumber.MAX_NUMBER) + 1;
            addNumber(lottoNumbers, randomNumber);
        }
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }

    private void addNumber(List<Integer> lottoNumbers, int randomNumber) {
        if (!lottoNumbers.contains(randomNumber)) {
            lottoNumbers.add(randomNumber);
        }
    }
}
