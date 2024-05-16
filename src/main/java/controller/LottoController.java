package controller;

import domain.GenerateRandomNumber;
import view.OutputView;
import java.util.List;

public class LottoController {

    private static final OutputView outputView = new OutputView();
    private static final GenerateRandomNumber generateRandomNumber = new GenerateRandomNumber();
    private static final int INITIAL_NUM = 0;

    public static void main(String[] args) {
        Main main = new Main();
        createLotto(main.Main());
    }

    private static void createLotto(int paper) {
        for (int i = INITIAL_NUM; i < paper; i++) {
            List<Integer> lottoList = generateRandomNumber.generateNumber();
            outputView.lotto(lottoList);
        }
    }
}
