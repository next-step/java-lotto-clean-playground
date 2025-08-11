package exception;

public enum ErrorMessage {

    INVALID_AMOUNT_VALUE("금액은 1000원 단위의 양수여야 합니다."),
    INVALID_NUMBER_FORMAT("정수를 입력해야 합니다."),
    INVALID_NUMBERS_SIZE("로또 번호는 6개여야 합니다."),
    DUPLICATE_WINNING_NUMBER("당첨 번호가 중복되었습니다."),
    INVALID_NUMBER_RANGE("로또 번호는 1~45 사이의 정수여야 합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호가 중복되었습니다."),
    INVALID_MANUAL_COUNT_NEGATIVE("수동 로또 개수는 0 이상이어야 합니다."),
    INVALID_MANUAL_COUNT_EXCEED("수동 로또 개수가 총 구매 가능한 로또 개수를 초과할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

