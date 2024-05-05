package view;

import domain.Lotto;

import java.util.List;

public class OutputView {

    public static void printStartMessage(){
        System.out.println("구입금액을 입력해 주세요.");
    }
    public static void printAmountMessage(int lottoAmount) {
        System.out.println(lottoAmount + "개를 구매했습니다.");
    }

    public static void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
