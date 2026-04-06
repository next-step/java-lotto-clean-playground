package model;

import common.NumberGenerator;
import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoFactory {
    NumberGenerator numberGenerator;

    public LottoFactory(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> generateLottoByPrice(int userCashInput) {
        int lottoCount = userCashInput / LottoSettingsConstants.LOTTO_PRICE;
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = generateLotto();
            result.add(lotto);
        }

        return result;
    }

    protected Lotto generateLotto(){
        Set<Integer> result = new HashSet<>();

        while(result.size() != LottoSettingsConstants.LOTTO_SIZE) {
            int pickedNumber = numberGenerator.generateNumber();
            result.add(pickedNumber);
        }

        return new Lotto(result.stream().toList());
    }

    public List<Lotto> mapToLottos(List<List<Integer>> numbers) {
        List<Lotto> result = new ArrayList<>();

        for (List<Integer> number: numbers) {
            result.add(new Lotto(number));
        }

        return result;
    }
}
