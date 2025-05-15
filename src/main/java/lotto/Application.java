package lotto;

import java.util.List;
import java.util.Scanner;

public class Application {
    private static final int LOTTO_MONEY = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("구입 금액 입력(금액은 1000원 이상 입력해주세요): ");
        int money = scanner.nextInt();
        if (money < 1000) {
            System.out.println("구매에 필요한 최소 금액은 1000원 입니다.");
        }
        int count = money / LOTTO_MONEY;
        for (int i = 0; i < count; i++) {
            List<Integer> lottoNumbers = LottoManage.shuffleNumbers();
            System.out.println(lottoNumbers);
        }
        scanner.close();
    }
}
