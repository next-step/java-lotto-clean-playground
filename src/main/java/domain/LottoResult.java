package domain;

public class LottoResult {
    private final int threeCorrectCount;
    private final int fourCorrectCount;
    private final int fiveCorrectCount;
    private final int sixCorrectCount;


    public LottoResult(int threeCorrectCount, int fourCorrectCount, int fiveCorrectCount, int sixCorrectCount) {
        this.threeCorrectCount = threeCorrectCount;
        this.fourCorrectCount = fourCorrectCount;
        this.fiveCorrectCount = fiveCorrectCount;
        this.sixCorrectCount = sixCorrectCount;
    }


    public int getThreeCorrectCount() {
        return threeCorrectCount;
    }

    public int getFourCorrectCount() {
        return fourCorrectCount;
    }

    public int getFiveCorrectCount() {
        return fiveCorrectCount;
    }

    public int getSixCorrectCount() {
        return sixCorrectCount;
    }
}
