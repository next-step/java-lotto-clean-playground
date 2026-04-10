package lotto;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();
    private static final LottoParser LOTTO_PARSER = new LottoParser();
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int price = inputPrice();
        if (price < 1000) {
            return;
        }

        LottoPurchase purchase = buyLottos(price);
        LottoReceipt receipt = purchase.printReceipt();
        displayReceiptInfo(receipt, purchase.getChange());

        displayResult(runDraw(receipt));
    }

    private static LottoPurchase buyLottos(int price) {
        int manualCount = inputManualCount();
        List<Lotto> manuals = inputManualNumbers(manualCount);

        LottoPurchase purchase = new LottoPurchase(price, manuals, LOTTO_MAKER);
        System.out.printf("\n수동 %d장, 자동 %d개를 구매했습니다.\n",
                manualCount, purchase.getNumberOfLotto() - manualCount);
        return purchase;
    }

    private static LottoDraw runDraw(LottoReceipt receipt) {
        System.out.println("\n지난주 당첨 번호를 입력해 주세요.");
        Lotto winningLotto = LOTTO_PARSER.parse(SC.nextLine());
        LottoNumber bonus = bonusBall();
        return new LottoDraw(winningLotto, bonus, receipt);
    }

    private static LottoNumber bonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusValue = Integer.parseInt(SC.nextLine());
            return new LottoNumber(bonusValue);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요.");
            return bonusBall();
        }
    }

    private static int inputPrice() {
        System.out.println("구입금액을 입력해 주세요. (ex. 1000)");
        try {
            int price = Integer.parseInt(SC.nextLine());
            if (price < 1000) {
                System.out.println("1000원 이상 입력해야 합니다. 로또를 살 수 없으므로 종료합니다.");
            }
            return price;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static int inputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SC.nextLine());
    }

    private static List<Lotto> inputManualNumbers(int count) {
        if (count <= 0) {
            System.out.println("로또를 구매할 수 없습니다.");

        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> LOTTO_PARSER.parse(SC.nextLine()))
                .toList();
    }

    private static void displayReceiptInfo(LottoReceipt receipt, int change) {
        receipt.lottos().getLottos().forEach(lotto -> {
            List<String> s = lotto.numbers().stream().map(Object::toString).toList();
            System.out.println("[" + String.join(", ", s) + "]");
        });
        if (change > 0) {
            System.out.println(change + "원이 남았습니다.");
        }
    }

    private static void displayResult(LottoDraw draw) {
        System.out.println("\n당첨 통계\n---------");
        for (LottoResult res : LottoResult.values()) {
            if (res == LottoResult.NONE) {
                continue;
            }
            displayRank(res, draw.getCount(res));
        }
        System.out.println("총 수익률은 " + draw.getRateOfReturn() + "입니다.");
    }

    private static void displayRank(LottoResult res, int count) {
        String label = res == LottoResult.BONUS ? "5개 일치, 보너스 볼 일치" : res.getMatchingCount() + "개 일치";
        System.out.printf("%s (%d원)- %d\n", label, res.getReward(), count);
    }
}
