package util;

import domain.Lotto;

public class Errors {

    public static final String INPUT_IS_NOT_INTEGER = "시도 횟수로는 정수가 입력되어야 합니다.";
    public static final String INPUT_PRICE_IS_NEGATIVE = "구입금액은 음수일 수 없습니다.";
    public static final String WRONG_LOTTO_SIZE = String.format("로또 숫자의 개수는 %d개여야 합니다.", Lotto.SIZE);
}
