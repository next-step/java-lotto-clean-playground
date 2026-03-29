package model;

import common.NumberGenerator;
import constants.LOTTO_SETTINGS;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {
    NumberGenerator numberGenerator;

    LottoFactory(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public Lotto generateLotto(){
        Set<Integer> result = new HashSet<>();

        while(result.size() != LOTTO_SETTINGS.LOTTO_SIZE) {
            int pickedNumber = numberGenerator.generateNumber();
            result.add(pickedNumber);
        }

        return new Lotto(result.stream().toList());
    }

}
