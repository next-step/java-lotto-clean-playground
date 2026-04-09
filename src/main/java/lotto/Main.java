package lotto;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        int totalPrice = Integer.parseInt(scanner.nextLine());
        System.out.println();

        LottoPurchase purchase = purchaseAndPrintLotto(totalPrice);
        LottoReceipt receipt = printReceipt(purchase);

        System.out.println();
        System.out.println("지난주 당첨 번호를 입력해 주세요.");
        WinningLotto winningLotto = parseWinningLotto(scanner.nextLine(), scanner.nextLine());

        LottoReceiptResult draw = new LottoReceiptResult(winningLotto, receipt);
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

    private static WinningLotto parseWinningLotto(String lottoNumberLine, String bonusLine) {
        LottoNumbers lottoNumbers = parseLottoNumbers(lottoNumberLine);
        LottoNumber bonusNumber = parseBonusNumber(bonusLine);

        return new WinningLotto(lottoNumbers, bonusNumber);
    }

    private static LottoNumbers parseLottoNumbers(String line) {
        List<LottoNumber> numbers = Stream.of(line.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .toList();

        return new LottoNumbers(numbers);
    }

    private static LottoNumber parseBonusNumber(String line) {
        int number = Integer.parseInt(line.trim());
        return new LottoNumber(number);
    }

    private static void printResult(LottoReceiptResult draw) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (5000원)- " + draw.getCount(LottoResult.THREE));
        System.out.println("4개 일치 (50000원)- " + draw.getCount(LottoResult.FOUR));
        System.out.println("5개 일치 (1500000원)- " + draw.getCount(LottoResult.FIVE));
        System.out.println("5개 일치, 보너스 볼 일치 (30000000원)- " + draw.getCount(LottoResult.FIVE_BONUS));
        System.out.println("6개 일치 (2000000000원)- " + draw.getCount(LottoResult.SIX));

        System.out.println("총 수익률은 " + draw.getRateOfReturn() + "입니다.");
    }
}
