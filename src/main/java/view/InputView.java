package view;
import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static int inputMoneyFromUser(){
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }
}
