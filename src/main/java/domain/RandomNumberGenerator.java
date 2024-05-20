package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    private static final int MAX = 45;
    private static final int COUNT_NUMBER = 1;
    private static final int LOTTO_SIZE = 6;
    private static final Random random = new Random();

    @Override
    public List<Integer> generateRandomNumber() {
        List<Integer> number = new ArrayList<>();
        while (number.size() < LOTTO_SIZE) {
            checkDuplicateNumber(number);
        }
        return number;
    }

    private static void checkDuplicateNumber(List<Integer> number) {
        int randomNumber = random.nextInt(MAX) + COUNT_NUMBER;
        if (!number.contains(randomNumber)) {
            number.add(randomNumber);
        }
    }
}
