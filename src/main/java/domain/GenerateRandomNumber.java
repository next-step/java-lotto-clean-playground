package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomNumber implements RandomNumber {
    private static final Random random = new Random();
    private static final int LOTTO_RANGE = 45;
    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_NUM = 1;

    @Override
    public List<Integer> generateNumber() {
        List<Integer> lottoNumber = new ArrayList<>();
        return generatedNumber(lottoNumber);
    }

    private static List<Integer> generatedNumber(List<Integer> lottoNumber) {
        while (lottoNumber.size() < LOTTO_COUNT) {
            int randomNumber = random.nextInt(LOTTO_RANGE) + LOTTO_NUM;
            generatedAddNumber(lottoNumber, randomNumber);
        }
        return lottoNumber;
    }

    private static void generatedAddNumber(List<Integer> lottoNumber, int randomNumber) {
        if (!lottoNumber.contains(randomNumber)) {
            lottoNumber.add(randomNumber);
        }
    }

}
