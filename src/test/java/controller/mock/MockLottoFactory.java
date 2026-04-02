package controller.mock;

import common.NumberGenerator;
import model.Lotto;
import model.LottoFactory;

import java.util.List;

public class MockLottoFactory extends LottoFactory {
    public boolean checkPriceHigherThanSingleLottoPriceCalled= false;
    public int generateLottoCalledCount = 0;
    public int getLottoCallCount = 0;

    public MockLottoFactory(NumberGenerator numberGenerator) {
        super(numberGenerator);
    }

    @Override
    public Lotto generateLotto() {
        this.generateLottoCalledCount++;
        return super.generateLotto();
    }

    @Override
    public List<Lotto> generateLottoByPrice(int userCashInput) {
        getLottoCallCount++;
        return super.generateLottoByPrice(userCashInput);
    }

   @Override
    protected void checkPriceHigherThanSingleLottoPrice(int price) {
        checkPriceHigherThanSingleLottoPriceCalled = true;
        super.checkPriceHigherThanSingleLottoPrice(price);
    }
}

