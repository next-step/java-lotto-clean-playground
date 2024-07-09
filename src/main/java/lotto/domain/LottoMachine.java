package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;

    public void buyLotto(int money, List<List<Integer>> manualNumbers) {
        money -= manualNumbers.size() * LOTTO_PRICE;
        LottoGroup lottos = new LottoGroup(manualNumbers);
        for (int i = 0; i < money; i += LOTTO_PRICE) {
            Lotto lotto = Lotto.create(new LottoNumberGenerator());
            lottos.add(lotto);
        }
        OutputView.printLotto(lottos.getNumbers());
        Map<LottoRate, Integer> rate = matchAnswer(lottos);
        double rateOfReturn = getRateOfReturn(money, rate);
        OutputView.printStatics(rate, rateOfReturn);
    }

    public Map<LottoRate, Integer> matchAnswer(LottoGroup lottos) {
        List<Integer> answerNumber = InputView.inputAnswerNumber();
        int bonusNumber = InputView.inputBonusNumber();
        AnswerLotto answerLotto = new AnswerLotto(answerNumber, bonusNumber);
        return answerLotto.calculateResult(lottos.getLottos());
    }

    private double getRateOfReturn(int money, Map<LottoRate, Integer> statics) {
        int earned = 0;
        for (Entry<LottoRate, Integer> entry : statics.entrySet()) {
            LottoRate rate = entry.getKey();
            int earnMoney = rate.getPrice() * entry.getValue();
            earned += earnMoney;
        }
        return (double)earned / money;
    }
}
