package view;

import domain.Lotto;
import java.util.List;
import java.util.Scanner;

public class LottoView {
    private static final Scanner scanner = new Scanner(System.in);

    public int readPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요");
        String input = scanner.nextLine().trim();
        int amount;

        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("0이상의 값을 입력하세요.");
        }
        return amount;
    }

    public void printLottoCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printLotto(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
