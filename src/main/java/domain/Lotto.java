package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {
    private List<Integer> lottoNumbers = new ArrayList<>();

    public Lotto() {
        generateLottoNumber();
    }

    private void generateLottoNumber() {
        lottoNumbers = new ArrayList<>();
        Random random = new Random();
        while (lottoNumbers.size() < 6) {
            int num = random.nextInt(45) + 1;
            if (!lottoNumbers.contains(num)) {
                lottoNumbers.add(num);
            }
        }
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    public static int calculateLottoAmount(int totalPrice) {
        return totalPrice / 1000;
    }
}
