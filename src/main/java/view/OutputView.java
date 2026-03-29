package view;

import domain.Lotto;
import domain.LottoNumber;

public class OutputView {
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void printInputPurchaseAmount(){
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }
    public static void printInputWinningNumber(){
        System.out.println(LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    public static void printLottos(LottoNumber lottoNumber) {
        for (Lotto lotto : lottoNumber.getLottoNumber()) {
            System.out.println(lotto.getNumbers());
        }
    }

}
