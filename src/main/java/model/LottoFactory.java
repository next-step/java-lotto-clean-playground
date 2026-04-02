package model;

import common.NumberGenerator;
import constants.ErrorMessageConstants;
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

    protected Lotto generateLotto(){
        Set<Integer> result = new HashSet<>();

        while(result.size() != LottoSettingsConstants.LOTTO_SIZE) {
            int pickedNumber = numberGenerator.generateNumber();
            result.add(pickedNumber);
        }

        return new Lotto(result.stream().toList());
    }

    protected void checkPriceHigherThanSingleLottoPrice(int price) {
       if (price < LottoSettingsConstants.LOTTO_PRICE){
           throw new IllegalArgumentException(ErrorMessageConstants.PRICE_TOO_LOW);
       }
    }

    public List<Lotto> generateLottoByPrice(int userCashInput) {
        checkPriceHigherThanSingleLottoPrice(userCashInput);

        int lottoCount = userCashInput / LottoSettingsConstants.LOTTO_PRICE;
        List<Lotto> result = new ArrayList<Lotto>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = generateLotto();
            result.add(lotto);
        }

        return result;
    }
}
