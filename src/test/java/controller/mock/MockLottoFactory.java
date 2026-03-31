package controller.mock;

import common.NumberGenerator;
import model.Lotto;
import model.LottoFactory;

public class MockLottoFactory extends LottoFactory {
    public int generateLottoCalledCount = 0;

    public MockLottoFactory(NumberGenerator numberGenerator) {
        super(numberGenerator);
    }

    @Override
    public Lotto generateLotto() {
        this.generateLottoCalledCount++;
        return super.generateLotto();
    }
}

