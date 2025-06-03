package util;

import java.util.List;

public class Validator {
    //구매 금액이 1000으로 나눠 떨이지지 않는 경우
    //수동 구매할 번호가 범위 내에 없을 경우
    //지난 주 당첨 번호가 6개보다 이상이거나 이하일 경우
    //지난 주 당첨 번호 중 1~45의 값이 아닌 경우

    public static void validateLottoPurchaseAmount(int amount) {
        if ((amount % 1000) != 0) {
            throw new IllegalArgumentException("구매금액은 1000원 단위로 입력하세요");
        }
    }

    public static void validateManualLottoNumberInRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("로또 번호는 1~45사이여야합니다");
            }
        }
    }

    public static void validateLastWeekNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다");
        }
    }

    public static void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1~45사이여야합니다");
        }
    }
}
