package view;

import domain.lotto.Money;

import java.util.Scanner;

public class InputView {
    
    public static Money inputMoney() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextInt());
    }
}
