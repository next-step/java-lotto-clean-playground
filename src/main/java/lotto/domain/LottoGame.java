package lotto.domain;

import java.util.List;

import lotto.Constant;
import lotto.NumberGenerator;
import lotto.RandomNumberGenerator;
import lotto.message.ErrorMessage;

public class LottoGame {
    private final int price;
    private List<Lotto> lottoList;
    private final int trial;
    private final NumberGenerator numberGenerator = new RandomNumberGenerator();

    public LottoGame(int price) {
        validatePrice(price);
        this.price = price;
        this.trial = getTrial();
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
