package view;

public class OutputView {
    public static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";

    public static void InputPurchaseAmount(){
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }
    public static void InputWinningNumber(){
        System.out.println(LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }
}
