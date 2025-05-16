package lotto;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static final int MINIMUM_AMOUNT = 1000;

    public static void main(String[] args) {
        int money = inputMoney();
        int count = money / MINIMUM_AMOUNT;
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = LottoManage.pullOutNumbers();
            LottoManage.shuffleNumbers(lottoNumbers);
            List<Integer> ticket = LottoManage.pickupLottoNumbers(lottoNumbers);
            System.out.println(ticket);
            System.out.println("내가 찾고 싶은 숫자" + ticket.contains(14));
        }
    }

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
