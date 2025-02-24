package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import lotto.Constant;
import lotto.generator.NumberGenerator;
import lotto.message.ErrorMessage;

public class LottoGame {
    private final int price;
    private final List<Lotto> lottoList = new ArrayList<>();
    private final int trial;
    private final NumberGenerator numberGenerator;
    private final CustomLotto customLotto;

    public LottoGame(int price, NumberGenerator numberGenerator, CustomLotto customLotto) {
        validatePrice(price);
        this.price = price;
        this.trial = getTrial();
        this.numberGenerator = numberGenerator;
        this.customLotto = customLotto;
        validateTrial();
        makeLottoList();
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    private void makeLottoList() {
        lottoList.addAll(customLotto.getCustomLottoList());

        for (int j = 0; j < trial - customLotto.getCustomLottoCount(); j++) {
            lottoList.add(new Lotto(numberGenerator));
        }
    }

    private void validatePrice(int price) {
        if ((price % 1000) != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    private int getTrial() {
        return price / Constant.LOTTO_PRICE;
    }

    private void validateTrial(){
        if(trial < customLotto.getCustomLottoCount()){
            throw new IllegalArgumentException(ErrorMessage.TOO_MANY_CUSTOM.getMessage());
        }
    }
}
