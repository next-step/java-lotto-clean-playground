package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum WinningLottosStatus {

    NOT_WINNING_LOTTOS(0, 0, false),
    THREE_CORRECT_LOTTOS(3, 5000, false),
    FOUR_CORRECT_LOTTOS(4, 50000, false),
    FIVE_CORRECT_LOTTOS(5, 1500000, false),
    FIVE_AND_BONUS_CORRECT_LOTTOS(5, 30000000, true),

    SIX_CORRECT_LOTTOS(6, 2000000000, false);

    private final int correctCount;
    private final int prizeMoney;
    private final boolean isSecondPrize;

    WinningLottosStatus(final int correctCount, final int prizeMoney, final boolean isSecondPrize) {
        this.correctCount = correctCount;
        this.prizeMoney = prizeMoney;
        this.isSecondPrize = isSecondPrize;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }


    public boolean isSecondPrize() {
        return isSecondPrize;
    }

    public static WinningLottosStatus of(final int correctCount, final boolean isSecondPrize) {
        return Arrays.stream(values())
                .filter(winningLottos -> winningLottos.correctCount == correctCount && winningLottos.isSecondPrize == isSecondPrize)
                .findFirst()
                .orElse(NOT_WINNING_LOTTOS);
    }

    public static Map<WinningLottosStatus, Integer> createWinningLottoCountStatus() {
        Map<WinningLottosStatus, Integer> winningLottoCountStatus = new HashMap<WinningLottosStatus, Integer>();
        Arrays.stream(values())
                .filter(winningLottosStatus -> winningLottosStatus != NOT_WINNING_LOTTOS)
                .forEach(winningLottosStatus -> winningLottoCountStatus.put(winningLottosStatus, 0));
        return winningLottoCountStatus;
    }

}
