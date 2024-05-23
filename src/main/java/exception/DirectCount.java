package exception;

import model.Consumer;
import model.Lotto;

public record DirectCount(
        int value
) {
    public static DirectCount from(final String directCount, final int inputMoney) {

        validateText(directCount);
        validateRange(Integer.parseInt(directCount), inputMoney);

        return new DirectCount(Integer.parseInt(directCount));
    }

    private static void validateText(final String directCount) {

        try {
            int num = Integer.parseInt(directCount);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    private static void validateRange(final int directCount, final int inputMoney) {

        if (directCount < 0)
            throw new IllegalArgumentException("[ERROR] 수동으로 구매할 로또 수는 음수일 수 없습니다.");

        if (Lotto.getPrice() * directCount > Consumer.getMoney())
            throw new IllegalArgumentException("[ERROR] 구입금액을 초과하여 입력하였습니다.");
    }
}
