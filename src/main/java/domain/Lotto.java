package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    public static final int PRICE_PER_TICKET = 1000;
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
        Collections.shuffle(lottoNumbers);
        Collections.sort(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
