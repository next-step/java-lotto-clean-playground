package controller;

import domain.Lotto;
import domain.LottoRandom;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoRandom lottoRandom = new LottoRandom();
        Lotto lotto = new Lotto();

        List<Integer> lottoPaper = lottoRandom.getLottoRandomNum();
        int money = inputView.getMoney();
        int num = lotto.getLottoNum(money);
        outputView.repeatLotto(num, lottoPaper);
    }
}
