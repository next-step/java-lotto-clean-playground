package domain;

public class WinnerNum {
    private int winnerNum;
    public WinnerNum(int winnerNum) {
        this.winnerNum = winnerNum;
    }

    public int getWinnerNum() {
        return winnerNum;
    }

    public void increase() {
        winnerNum++;
    }
}
