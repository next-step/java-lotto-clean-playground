package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomNumber implements RandomNumber {

    private static final Random random = new Random();
    private static final int MAX = 45;
    private static final int COUNT_NUMBER = 1;
    private static final int LOTTO_COUNT = 6;

    @Override
    public List<Integer> generateNumber() {
        List<Integer> lottoNumber = new ArrayList<>();
        return removeDuplicateNumber(lottoNumber);
    }

    private List<Integer> removeDuplicateNumber(List<Integer> lottoNumber) {
        while (lottoNumber.size() < LOTTO_COUNT) {
            int randomNumber = random.nextInt(MAX) + COUNT_NUMBER;
            generatedAddNumber(lottoNumber, randomNumber);
        }
        return lottoNumber;
    }

    private void generatedAddNumber(List<Integer> lottoNumber, int randomNumber) {
        if (!lottoNumber.contains(randomNumber)) {
            lottoNumber.add(randomNumber);
        }
    }
}
