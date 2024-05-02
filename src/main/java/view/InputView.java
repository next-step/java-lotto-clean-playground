package view;

import java.util.Scanner;

public class InputView {

    private final static String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNER_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);


    public String inputPrice(){
        System.out.println(INPUT_PRICE);
        return scanner.nextLine();
    }

    public String inputWinnerNumber(){
        System.out.println();
        System.out.println(INPUT_WINNER_NUMBER);
        return scanner.nextLine();
    }
}
