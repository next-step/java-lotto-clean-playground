package model.exception;

public class ExceptionMessage {

    // BonusNumber
    public static final String BONUS_BALL_IN_WINNING_LOTTO_ERROR_MESSAGE = "보너스 볼은 당첨 번호에 포함되어 있으면 안됩니다.";

    // Lotto
    public static final String LOTTO_SIZE_ERROR_MESSAGE = "로또는 6개의 숫자로 구성되어야 합니다.";
    public static final String LOTTO_HAS_SAME_NUMBER_ERROR_MESSAGE = "로또 내 동일한 숫자가 있으면 안됩니다.";

    // LottoNumber
    public static final String LOTTO_NUMBER_NOT_IN_1_TO_45_ERROR_MESSAGE = "로또 번호 및 보너스 볼은 1과 45사이의 숫자이어야 합니다.";
    public static final String LOTTO_NUMBER_INPUT_ERROR_MESSAGE = "숫자입력만 허용합니다.";

    // LottoPurchaseMoney
    public static final String LOTTO_PURCHASE_MONEY_UNDER_ZERO_ERROR_MESSAGE = "구입금액은 음수가 될 수 없습니다.";
    public static final String LOTTO_PURCHASE_MONEY_NOT_DIVIDED_1000_ERROR_MESSAGE = "구입금액은 1000단위이어야 합니다.";

    // ManualBuyCount
    public static final String MANUAL_BUY_COUNT_UNDER_ZERO_ERROR_MESSAGE = "수동 구매 개수는 음수가 될 수 없습니다.";
    public static final String MANUAL_BUY_COUNT_INPUT_ERROR_MESSAGE = "수동 구매 개수는 숫자이어야 합니다.";
    public static final String MANUAL_BUY_COST_OVER_PURCHASE_MONEY_ERROR_MESSAGE = "수동 구매 비용이 구입금액 비용을 넘을 수 없습니다.";
}
