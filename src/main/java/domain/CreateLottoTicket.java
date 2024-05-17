package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateLottoTicket implements RandomNumber {

    private static final int MAX = 45;
    private static final int COUNT_NUMBER = 1;
    private static final int LOTTO_SIZE = 6;

    private List<Integer> lottoNumber;

    private static final Random random = new Random();

    public CreateLottoTicket() {
        this.lottoNumber = generateRandomNumber();
    }

    public List<Integer> getLottoNumber() {
        return new ArrayList<>(lottoNumber);
    }

    @Override
    public List<Integer> generateRandomNumber() {
        List<Integer> number = new ArrayList<>();
        while (number.size() < LOTTO_SIZE) {
            int randomNumber = random.nextInt(MAX) + COUNT_NUMBER;
            if (!number.contains(randomNumber)) {
                number.add(randomNumber);
            }
        }
        return number;
    }
}
