package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberGenerator {

    public LottoNumberGenerator() {
    }

    public List<Integer> generateLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        while (lottoNumbers.size() < Lotto.LOTTO_NUMBER_COUNT) {
            int randomNumber = (int) (Math.random() * LottoNumber.MAX_NUMBER) + LottoNumber.MIN_NUMBER;
            addNumber(lottoNumbers, randomNumber);
        }

        return lottoNumbers;
    }

    private void addNumber(List<Integer> lottoNumbers, int randomNumber) {
        if (!lottoNumbers.contains(randomNumber)) {
            lottoNumbers.add(randomNumber);
        }
    }
}
