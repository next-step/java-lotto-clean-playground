package constant;

public enum ErrorMessage {
    INVALID_INPUT_FORMAT("정수만 입력할 수 있습니다."),
    INVALID_NEGATIVE_INPUT("양수만 입력할 수 있습니다."),
    INVALID_LOTTO_COUNT("로또 번호는 6개여야 합니다."),
    INVALID_LOTTO_RANGE("로또 번호는 1부터 45 사이만 입력 가능해야 합니다."),
    INVALID_DUPLICATE_NUMBER("로또 번호에 중복된 숫자가 있습니다."),
    INVALID_DUPLICATE_BONUS_NUMBER("보너스 숫자가 지난 주 당첨 번호와 중복됩니다."),
    INVALID_PURCHASE_UNIT("로또는 천 원 단위로 구매 가능합니다."),
    INVALID_PURCHASE_AMOUNT("로또는 최소 구매 금액은 1000원입니다."),
    INVALID_MANUAL_QUANTITY("수동 구매 가능한 수를 초과했습니다."),
    OVERFLOW("오버플로우가 발생했습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
