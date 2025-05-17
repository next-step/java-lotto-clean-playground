import java.util.*;

public class InputReader {
    public static int readPurchaseAmount(Scanner sc) {
        System.out.println("구입금액을 입력해 주세요.");
        int amount = sc.nextInt();
        validatePurchaseAmount(amount);
        return amount;
    }

    private static void validatePurchaseAmount(int amount) {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("로또 한 장의 단위는 1000원입니다.");
        }
    }
}
