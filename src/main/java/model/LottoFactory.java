package model;

import common.NumberGenerator;
import constants.LottoSettingsConstants;

import java.util.HashSet;
import java.util.Set;

public class LottoFactory {
    NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public Lotto generateLotto(){
        Set<Integer> result = new HashSet<>();

        while(result.size() != LottoSettingsConstants.LOTTO_SIZE) {
            int pickedNumber = numberGenerator.generateNumber();
            result.add(pickedNumber);
        }

        return new Lotto(result.stream().toList());
    }
}
