package lotto.message;

public enum ConsoleMessage {
    INPUT_MONEY("구입금액을 입력해 주세요."),
    COUNT_LOTTO("수동으로 %d장, 자동으로 %d개를 구매했습니다."),
    LAST_WIN_NUM("지난 주 당첨 번호를 입력해 주세요."),
    WINNING_STATISTICS("당첨 통계"),
    CHECK_SAME("%d개 일치 (%d원) - %d개"),
    CHECK_SAME_2ND("%d개 일치, 보너스 볼 일치 (%d원) -%d개"),
    REWARD_RATE("총 수익률은 %s입니다."),
    INPUT_BONUS("보너스 볼을 입력해 주세요."),
    CUSTOM_LOTTO_COUNT("수동으로 구매할 로또 수를 입력해 주세요."),
    CUSTOM_LOTTO_INPUT("수동으로 구매할 번호를 입력해 주세요.")
    ;

    private final String message;

    ConsoleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
