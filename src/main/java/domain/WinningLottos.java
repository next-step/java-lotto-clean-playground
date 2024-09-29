package domain;

import java.util.Arrays;

public enum WinningLottos {

    THREE_CORRECT_LOTTOS(3, 5000, 0),
    FOUR_CORRECT_LOTTOS(4, 50000, 0),
    FIVE_CORRECT_LOTTOS(5, 1500000, 0),
    SIX_CORRECT_LOTTOS(6, 2000000000, 0);

    private final int correctCount;
    private final int prizeMoney;
    private int lottoCount;

    WinningLottos(int correctCount, int prizeMoney, int lottoCount) {
        this.correctCount = correctCount;
        this.prizeMoney = prizeMoney;
        this.lottoCount = lottoCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public void addWinnerLotto() {
        this.lottoCount += 1;
    }

    public static WinningLottos of(final int correctCount) {
        return Arrays.stream(values())
                .filter(winningLottos -> winningLottos.correctCount == correctCount)
                .findFirst()
                .orElse(null);
    }
}
