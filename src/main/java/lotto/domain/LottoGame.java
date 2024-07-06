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

    public LottoGame(int price, NumberGenerator numberGenerator) {
        validatePrice(price);
        this.price = price;
        this.trial = getTrial();
        this.numberGenerator = numberGenerator;
        makeLottoList();
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    private void makeLottoList() {
        for (int i = 0; i < trial; i++) {
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
}
