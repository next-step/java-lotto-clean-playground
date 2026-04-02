package lotto;

import java.util.Scanner;

public class Main {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        int totalPrice = scanner.nextInt();

        System.out.println();
        purchaseAndPrintLotto(totalPrice);
    }

    private static void purchaseAndPrintLotto(int totalPrice) {
        LottoPurchase purchase = new LottoPurchase(totalPrice, LOTTO_MAKER);

        System.out.println(purchase.getNumberOfLotto() + "개를 구매했습니다.");

        LottoReceipt receipt = purchase.printReceipt();
        receipt.printToConsole();

        int change = purchase.getChange();
        if (change != 0) {
            System.out.println(change + "원이 남았습니다.");
        }
    }
}
