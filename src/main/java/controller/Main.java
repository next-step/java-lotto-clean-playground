package controller;

import domain.CountLotto;
import domain.GenerateRandomNumber;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {

    private static final int INITIAL_NUM = 0;

    public void Main() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        CountLotto countLotto = new CountLotto();
        GenerateRandomNumber generateRandomNumber = new GenerateRandomNumber();

        int money = inputView.getMoney();
        int paper = countLotto.getCountLotto(money);

        for (int i = INITIAL_NUM; i < paper; i++) {
            List<Integer> lottoList = generateRandomNumber.generateNumber();
            outputView.lotto(lottoList);
        }
    }
}
