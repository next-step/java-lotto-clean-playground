package io.suhan.lotto;

import io.suhan.lotto.model.lotto.LottoController;
import io.suhan.lotto.view.InputView;

public class Main {
    public static void main(String[] args) {
        try {
            LottoController controller = new LottoController();
            int balance = InputView.getBalance();

            controller.executePurchase(balance);
            controller.executeDraw(balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
