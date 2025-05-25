package lotto.input;

import java.util.Scanner;

public class PassivityNumberInput {
    private final Scanner scanner = new Scanner(System.in);
    private final int totalTicket;

    public PassivityNumberInput(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public int passivityLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        while (count < 0 || count > totalTicket) {
            System.out.println("잘못된 입력입니다 구입 금액에 맞춰 다시 작성해주세요");
            count = scanner.nextInt();
        }
        return count;
    }


}
