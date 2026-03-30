package controller;

import model.Lotto;
import model.LottoBatch;

import java.util.List;

public class MockLottoBatch extends LottoBatch {
    public boolean getMatchCountPerLottoCalled = false;

    MockLottoBatch(List<Lotto> lottos){
        super(lottos);
    }

    @Override
    public List<Integer> getMatchCountPerLotto(List<Integer> winningNumbers) {
        getMatchCountPerLottoCalled = true;
        return super.getMatchCountPerLotto(winningNumbers);
    }
}
