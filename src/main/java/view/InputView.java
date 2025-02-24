package view;

import domain.*;

import java.util.List;
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
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = scanner.nextInt();
        return new WinningNumbers(input, bonusNumber);
    }

    public static long inputAmountOfManualLottosFromUser(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLong();
    }

    public static List<Integer> inputLottoNumberFromUser(){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        String input = scanner.next();
        return Utils.parseNumbers(input);
    }

}
