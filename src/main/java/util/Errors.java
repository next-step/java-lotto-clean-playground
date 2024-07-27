package util;

import domain.Lotto;
import domain.LottoNumber;

public class Errors {

    public static final String INPUT_IS_NOT_INTEGER = "시도 횟수로는 정수가 입력되어야 합니다.";
    public static final String INPUT_PRICE_IS_NEGATIVE = "구입금액은 음수일 수 없습니다.";
    public static final String WRONG_LOTTO_SIZE = String.format("로또 숫자의 개수는 %d개여야 합니다.", Lotto.SIZE);
    public static final String NUMBER_IS_NOT_IN_VALID_RANGE = String.format("각 숫자는 %d~%d사이의 숫자입니다.",
                                                                            LottoNumber.MIN_VALUE,
                                                                            LottoNumber.MAX_VALUE);
    public static final String INPUT_NUMBER_IS_NOT_INTEGER = "당첨 숫자로는 정수가 입력되어야 합니다.";
    public static final String NUMBERS_HAS_DUPLICATE_NUMBER = "로또 내 각 숫자는 중복될 수 없습니다.";
    public static final String BONUS_NUMBER_IS_IN_LOTTO_NUMBER = "이미 로또 당첨번호로 뽑힌 번호입니다.";
    public static final String SORT_LOTTO_NUMBER_ERROR = "로또 번호(LottoNumber)는 로또 번호끼리만 대소 비교할 수 있습니다.";
}
