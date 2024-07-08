package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.Lotto;
import domain.LottoGenerator;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_PRICE = 1_000;

    public static int inputPurchasePrice() {
        System.out.println("구입 금액을 입력해 주세요.");
        int purchaseCount = Integer.parseInt(scanner.nextLine()) / LOTTO_PRICE;
        return purchaseCount;
    }

    public static int inputManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Lotto> inputManualLottoNumber(int manualCount) {
        LottoGenerator lottoGenerator = new LottoGenerator();
        List<Lotto> lottoList = new ArrayList<>();

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            Lotto lotto = lottoGenerator.generateManualLotto(scanner.nextLine());
            lottoList.add(lotto);
        }
        return lottoList;
    }

    public static String inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

}
