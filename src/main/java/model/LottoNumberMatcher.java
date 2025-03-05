package model;

import java.util.function.Supplier;

public class LottoNumberMatcher {

    private int numberEqualCount;

    public void increaseIfMatch(Supplier<Boolean> lottoNumberCompareExpression) {
        if (lottoNumberCompareExpression.get()) {
            this.numberEqualCount++;
        }
    }

    public int getNumberEqualCount() {
        return this.numberEqualCount;
    }

}
