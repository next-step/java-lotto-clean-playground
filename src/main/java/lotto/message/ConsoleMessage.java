package lotto.message;

public enum ConsoleMessage {
    INPUT_MONEY("구입금액을 입력해 주세요."),
    COUNT_LOTTO("%d개를 구매했습니다.");

    private final String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
