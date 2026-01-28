package lotto.view;

import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    private InputView() { // private 생성자를 만들어 외부에서 생성자 호출 방지
    }

    private static <T> T repeatUntilSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return repeatUntilSuccess(() -> {
            String input = sc.nextLine();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("구입금액은 빈 값일 수 없습니다.");
            }
            return input;
        });
    }

}
