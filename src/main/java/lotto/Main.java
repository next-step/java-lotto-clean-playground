package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();
    private static final LottoParser LOTTO_PARSER = new LottoParser();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalPrice, numberOfManual;

        System.out.println("구입금액을 입력해 주세요. (ex. 1000) (숫자가 아닌 경우 0으로 간주)");

        try {
            totalPrice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            totalPrice = 0;
        }

        System.out.println();

        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        numberOfManual = Integer.parseInt(scanner.nextLine());

        System.out.println();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < numberOfManual; i++) {
            manualLottos.add(LOTTO_PARSER.parse(scanner.nextLine()));
        }

        LottoPurchase purchase = new LottoPurchase(totalPrice, manualLottos, LOTTO_MAKER);

        System.out.println("\n수동으로 " + numberOfManual + "장, 자동으로 " +
                (purchase.getNumberOfLotto() - numberOfManual) + "개를 구매했습니다.");

        LottoReceipt receipt = purchase.printReceipt();
        displayReceipt(receipt);
        displayChange(purchase.getChange());

        System.out.println();
        System.out.println("지난주 당첨 번호를 입력해 주세요.");
        Lotto drawnLotto = LOTTO_PARSER.parse(scanner.nextLine());

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(scanner.nextLine());

        LottoDraw draw = new LottoDraw(drawnLotto, bonusNumber, receipt);
        System.out.println();
        displayResult(draw);
    }


    private static void displayReceipt(LottoReceipt receipt) {
        for (Lotto lotto : receipt.lottos().getLottos()) {
            List<String> nums = lotto.numbers().stream()
                    .map(LottoNumber::toString)
                    .toList();
            System.out.println("[" + String.join(", ", nums) + "]");
        }
    }

    private static void displayChange(int change) {
        if (change != 0) {
            System.out.println(change + "원이 남았습니다.");
        }
    }

    private static void displayResult(LottoDraw draw) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        System.out.println("3개 일치 (" + LottoResult.THREE.getReward() + "원)- " + draw.getCount(LottoResult.THREE));
        System.out.println("4개 일치 (" + LottoResult.FOUR.getReward() + "원)- " + draw.getCount(LottoResult.FOUR));
        System.out.println("5개 일치 (" + LottoResult.FIVE.getReward() + "원)- " + draw.getCount(LottoResult.FIVE));
        System.out.println(
                "5개 일치, 보너스 볼 일치 (" + LottoResult.BONUS.getReward() + "원)- " + draw.getCount(LottoResult.BONUS));
        System.out.println("6개 일치 (" + LottoResult.SIX.getReward() + "원)- " + draw.getCount(LottoResult.SIX));

        System.out.println("총 수익률은 " + draw.getRateOfReturn() + "입니다.");
    }

}