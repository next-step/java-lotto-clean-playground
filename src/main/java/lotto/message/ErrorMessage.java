package lotto.message;

public enum ErrorMessage {
    INVALID_MONEY("천원 단위로 입력해주세요!"),
    INVALID_LOTTO_NUM("로또의 개수가 이상합니다!"),
    INVALID_NUM("유효한 숫자가 아닙니다!"),
    ;

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
