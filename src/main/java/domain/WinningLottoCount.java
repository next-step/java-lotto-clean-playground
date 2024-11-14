package domain;

import java.util.Map;

public class WinningLottoCount {

    private final Map<WinningLottosStatus, Integer> winningLottoCountStatus;

    public WinningLottoCount() {
        this.winningLottoCountStatus = WinningLottosStatus.createWinningLottoCountStatus();
    }

    public void updateWinningLottoCountStatus(WinningLottosStatus winningLottosStatus) {
        winningLottoCountStatus.replace(winningLottosStatus, winningLottoCountStatus.get(winningLottosStatus) + 1);
    }

    public Map<WinningLottosStatus, Integer> getWinningLottoCountStatus() {
        return winningLottoCountStatus;
    }
}
