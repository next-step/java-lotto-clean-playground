package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    public static final int PRICE_PER_TICKET = 1000;
    private List<Integer> lottoNumbers = new ArrayList<>();
    private NumberGenerator numberGenerator; // NumberGenerator 객체 추가

    public Lotto() {
        this.numberGenerator = new NumberGenerator(); // NumberGenerator 객체 초기화
        generateLottoNumber();
    }

    public Lotto(List<Integer> numbers) {
        this.lottoNumbers.addAll(numbers);
    }

    private void generateLottoNumber() {
        List<Integer> numbers = numberGenerator.generateRandomNumbers();
        Collections.shuffle(numbers);
        Collections.sort(numbers);
        lottoNumbers.addAll(numbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}