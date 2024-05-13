package model;

public class Constant {

    public static final int LOTTO_PRICE = 1000;
    public static final int LOTTO_SIZE = 6;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int THREE_COUNT = 3;
    public static final int FOUR_COUNT = 4;
    public static final int FIVE_COUNT = 5;
    public static final int SIX_COUNT = 6;
    public static final int ZERO_COUNT = 0;

    public static final int FIFTH_PRICE = 5000;
    public static final int FOURTH_PRICE = 50000;
    public static final int THIRD_PRICE = 1500000;
    public static final int SECOND_PRICE = 30000000;
    public static final int FIRST_PRICE = 2000000000;

    public static final String SEPARATOR = ",";

    // InputView

    public static final String PRICE_QUERY = "구입금액을 입력해 주세요.";
    public static final String WIN_LOTTO_QUERY = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_QUERY = "보너스 볼을 입력해 주세요";
    public static final String MANUAL_COUNT_QUERY = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String MANUAL_LOTTO_QUERY = "수동으로 구매할 번호를 입력해 주세요.";


    // ResultView
    public static final String WIN_RESULT = "당첨 통계";
    public static final String LINES = "---------";
    public static final String LOTTO_BUY_RESULT_1 = "수동으로 ";
    public static final String LOTTO_BUY_RESULT_2 = "장 자동으로";
    public static final String LOTTO_BUY_RESULT_3 = "개를 구매했습니다.";
    public static final String PROFIT_RESULT_1 = "총 수익률은 ";
    public static final String PROFIT_RESULT_2 = "입니다.";
    public static final String STATICS_RESULT_1 = "개 일치";
    public static final String STATICS_RESULT_2 = " 보너스볼 일치";
    public static final String STATICS_RESULT_3 = "원";
    public static final String STATICS_RESULT_4 = "개";
    public static final String OPEN_PARENTHESES = "(";
    public static final String CLOSE_PARENTHESES = ")";
    public static final String DASH = "-";
    public static final String BLANK = " ";

    //exceptions
    public static final String RANGE_EXCEPTION = "로또 번호는 1부터 45 사이여야 합니다.";
    public static final String SIZE_EXCEPTION = "로또 번호는 6개입니다.";
    public static final String DUPLICATE_EXCEPTION = "로또 번호는 중복이 불가합니다.";
    public static final String VALUE_EXCEPTION = "로또 번호는 숫자여야 합니다.";
    public static final String PRICE_VALUE_EXCEPTION = "가격은 숫자여야 합니다.";
    public static final String BONUS_DUPLICATE_EXCEPTION = "보너스 볼은 중복이 불가합니다.";
    public static final String LOTTO_PRICE_EXCEPTION = "로또는 천원 단위로만 구입이 가능합니다.";


}
