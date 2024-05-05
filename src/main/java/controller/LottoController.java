package controller;

import model.Lotto;
import model.RankCalculator;
import view.LottoOutput;

import java.util.List;

public class LottoController {

    private RankCalculator rankCalculator;
    private Lotto lotto;

    public LottoController(RankCalculator rankCalculator, Lotto lotto) {
        this.rankCalculator = rankCalculator;
        this.lotto = lotto;
    }

    public void run() {

    }
}
