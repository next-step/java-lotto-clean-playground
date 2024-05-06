package lotto.domain;

import lotto.dto.Lotto;

import java.util.*;

public class CreateLotto {
    public Lotto createLotto() {
        Random rand = new Random();
        List<Integer> randomNumbers = new ArrayList<>();

        // 한 로또 내에 난수가 중복되지 않도록 HashSet을 사용한다
        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < 6) {
            int randomNumber = rand.nextInt(45) + 1;
            uniqueNumbers.add(randomNumber);
        }

        randomNumbers.addAll(uniqueNumbers);

        return new Lotto(randomNumbers);
    }
}
