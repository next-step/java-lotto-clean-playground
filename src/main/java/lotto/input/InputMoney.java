package lotto.input;

import java.util.Scanner;

public class InputMoney {
    private static final int MINIMUM_AMOUNT = 1000;

    private static int inputMoney() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("구입 금액 입력(금액은 1000원 이상 입력해주세요): ");
        int money = scanner.nextInt();
        while (money < MINIMUM_AMOUNT) {
            System.out.println("구매에 필요한 금액은 1000원 이상입니다 다시 입력해주세요");
            money = scanner.nextInt();
        }
        return money;
    }
}
