package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;
import lotto.domain.parser.LottoParser;
import lotto.domain.model.Money;
import lotto.domain.model.WinningLotto;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    private InputView() {}

    private static <T> T input(String message, Supplier<T> supplier) {
        while (true) {
            try {
                System.out.println(message);
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

    public static Money inputMoney() {
        return input("구입 금액을 입력해 주세요.", () -> {
            String input = sc.nextLine();
            return new Money(input);
        });
    }

    public static int inputManualCount(int maxCount) {
        return input("수동으로 구매할 로또 수를 입력해 주세요.", () -> {
            String input = sc.nextLine();
            int count = LottoParser.parseToInt(input);

            if (count < 0 || count > maxCount) {
                throw new IllegalArgumentException("수동 구매 수는 0에서 " + maxCount + " 사이여야 합니다.");
            }
            return count;
        });
    }

    public static List<Lotto> inputManualLotto(int manualCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            lottos.add(inputSingleManualLotto()); // 한 줄씩 재입력 받기
        }
        return lottos;
    }

    private static Lotto inputSingleManualLotto() {
        return input("", InputView::readLotto);
    }

    public static WinningLotto inputWinningLotto() {
        Lotto winningNumbers = input("지난 주 당첨 번호를 입력해주세요.", InputView::readLotto);

        return input("보너스 볼을 입력해주세요", () -> {
            LottoNumber bonusNumber = LottoNumber.valueOf(LottoParser.parseToInt(sc.nextLine()));
            return WinningLotto.of(winningNumbers, bonusNumber);
        });
    }

    private static Lotto readLotto() {
        List<Integer> rawNumbers = LottoParser.parseNumbers(sc.nextLine());
        List<LottoNumber> lottoNumbers = rawNumbers.stream()
            .map(LottoNumber::valueOf)
            .toList();
        return Lotto.from(lottoNumbers);
    }
}
