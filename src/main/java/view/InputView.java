package view;

import lotto.Lotto;
import lotto.LottoNumber;
import lotto.Lottos;
import lotto.PurchaseAmount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public PurchaseAmount readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return new PurchaseAmount(Integer.parseInt(scanner.nextLine().trim()));
    }

    public Lotto readWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return parseLotto(scanner.nextLine().trim());
    }

    public LottoNumber readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return parseBonusNumber(scanner.nextLine().trim());
    }

    public int readManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine().trim());
    }

    public Lottos readManualLottos(int manualLottoCount) {
        if (manualLottoCount == 0) {
            return new Lottos(new ArrayList<>());
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return readLottos(manualLottoCount);
    }

    private Lottos readLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < lottoCount; count++) {
            lottos.add(parseLotto(scanner.nextLine().trim()));
        }
        return new Lottos(lottos);
    }

    static Lotto parseLotto(String input) {
        List<LottoNumber> numbers = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        while (tokenizer.hasMoreTokens()) {
            numbers.add(toLottoNumber(tokenizer.nextToken()));
        }
        return new Lotto(numbers);
    }

    static LottoNumber parseBonusNumber(String input) {
        return toLottoNumber(input);
    }

    private static LottoNumber toLottoNumber(String value) {
        return new LottoNumber(Integer.parseInt(value.trim()));
    }
}
