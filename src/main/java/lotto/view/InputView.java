package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import lotto.domain.casher.Money;
import lotto.domain.model.LottoParser;
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
        return input("지난 주 당첨 번호를 입력해주세요.", () -> {
            String input = sc.nextLine();
            List<Integer> numbers = LottoParser.stringToLotto(input);
            return new WinningLotto(numbers); // 파싱 에러도 여기서 다 잡힙니다.
        });
    }

}
