package lotto.view;

import java.util.Scanner;
import java.util.function.Supplier;
import lotto.domain.model.Lotto;
import lotto.domain.model.LottoNumber;
import lotto.domain.model.LottoParser;
import lotto.domain.model.Money;
import lotto.domain.model.WinningLotto;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    private InputView() { // private 생성자를 만들어 외부에서 생성자 호출 방지
    }

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

    public static WinningLotto inputWinningLotto() {
        Lotto winningNumbers = input("지난 주 당첨 번호를 입력해주세요.", () -> {
            return Lotto.from(LottoParser.parseWinningNumbers(sc.nextLine()));
        });

        return input("보너스 볼을 입력해주세요", () -> {
            LottoNumber bonusNumber = new LottoNumber(LottoParser.stringToInt(sc.nextLine()));
            return new WinningLotto(winningNumbers, bonusNumber); // 생성자에서 중복 검사!
        });
    }
}
