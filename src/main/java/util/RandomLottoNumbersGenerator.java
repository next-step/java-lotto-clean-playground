package util;

import model.LottoNumber;
import model.LottoNumbers;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static model.LottoConstraints.*;

public class RandomLottoNumbersGenerator {

    private static final Random RANDOM = new Random();

    public static LottoNumbers getRandomLottoNumbers() {
        Set<LottoNumber> lottoNumbers = new HashSet<>();

        while (smallThanMaxSize(lottoNumbers)) {
            LottoNumber randomLottoNumber = createRandomLottoNumber();
            lottoNumbers.add(randomLottoNumber);
        }

        return new LottoNumbers(lottoNumbers);
    }

    private static LottoNumber createRandomLottoNumber() {
        int randomInteger = createRandomInteger();

        return new LottoNumber(randomInteger);
    }

    private static int createRandomInteger() {
        return RANDOM.nextInt(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER);
    }

    private static boolean smallThanMaxSize(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() < LOTTO_SIZE;
    }

}
