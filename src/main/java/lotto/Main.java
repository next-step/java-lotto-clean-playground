package lotto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalPrice;

        System.out.println("구입금액을 입력해 주세요.");

        try{
            totalPrice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            totalPrice = 0;
        }

        System.out.println();

        LottoPurchase purchase = purchaseAndPrintLotto(totalPrice);
        LottoReceipt receipt = printReceipt(purchase);

        System.out.println();
        System.out.println("지난주 당첨 번호를 입력해 주세요.");
        Lotto drawnLotto = parseDrawnLotto(scanner.nextLine());

        LottoDraw draw = new LottoDraw(drawnLotto, receipt);
        System.out.println();
        printResult(draw);
    }

    private static LottoPurchase purchaseAndPrintLotto(int totalPrice) {
        LottoPurchase purchase = new LottoPurchase(totalPrice, LOTTO_MAKER);
        System.out.println(purchase.getNumberOfLotto() + "개를 구매했습니다.");
        return purchase;
    }

    private static LottoReceipt printReceipt(LottoPurchase purchase) {
        LottoReceipt receipt = purchase.printReceipt();
        receipt.printToConsole();

        int change = purchase.getChange();
        if (change != 0) {
            System.out.println(change + "원이 남았습니다.");
        }

        return receipt;
    }

    private static Lotto parseDrawnLotto(String line) {
        List<LottoNumber> numbers = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();

        return new Lotto(numbers);
    }

    private static void printResult(LottoDraw draw) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원)- " + draw.getCount(LottoResult.THREE));
        System.out.println("4개 일치 (50000원)- " + draw.getCount(LottoResult.FOUR));
        System.out.println("5개 일치 (1500000원)- " + draw.getCount(LottoResult.FIVE));
        System.out.println("6개 일치 (2000000000원)- " + draw.getCount(LottoResult.SIX));

        System.out.println("총 수익률은 " + draw.getRateOfReturn() + "입니다.");
    }
}
