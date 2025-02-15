package view;

import domain.*;
import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static long inputMoneyFromUser(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLong();
    }

    public static WinningNumbers inputWinningNumberFromUser(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.next();
        return new WinningNumbers(input);
    }
}
