package lotto;

import java.util.List;
import java.util.Scanner;

public class LottoInput {
    private static final LottoMaker LOTTO_MAKER = new LottoMaker();
    private static final LottoParser LOTTO_PARSER = new LottoParser();
    private static final Scanner SCANNER = new Scanner(System.in);

    public int inputPrice() {
        int price = tryParsePrice();
        if (price < 1000) {
            System.out.println("1000원 이상 입력해야 합니다.");
            return 0;
        }
        return price;
    }

    private static int tryParsePrice() {
        System.out.println("구입금액을 입력해 주세요. (ex. 1000)");
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static int inputManualCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요. (구매 로또 수 이하여야 합니다.)");
        return Integer.parseInt(SCANNER.nextLine());
    }

    private static List<Lotto> inputManualNumbers(int count) {
        if (count <= 0) {
            System.out.println("로또를 구매할 수 없습니다.");

        }
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> LOTTO_PARSER.parse(SCANNER.nextLine()))
                .toList();
    }

    public LottoPurchase buyLottos(int price) {
        int manualCount = price + 1;
        int count = price / 1000;

        while (manualCount > count) {
            manualCount = inputManualCount();
        }

        List<Lotto> manuals = inputManualNumbers(manualCount);

        LottoPurchase purchase = new LottoPurchase(price, manuals, LOTTO_MAKER);
        System.out.printf("\n수동 %d장, 자동 %d개를 구매했습니다.\n",
                manualCount, purchase.getNumberOfLotto() - manualCount);
        return purchase;
    }
}
