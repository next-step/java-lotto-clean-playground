package domain;

public class LottoResult {
    private final Integer threeCorrectCount;
    private final Integer fourCorrectCount;
    private final Integer fiveCorrectCount;
    private final Integer sixCorrectCount;


    public LottoResult(Integer threeCorrectCount,
                       Integer fourCorrectCount,
                       Integer fiveCorrectCount,
                       Integer sixCorrectCount) {
        this.threeCorrectCount = threeCorrectCount;
        this.fourCorrectCount = fourCorrectCount;
        this.fiveCorrectCount = fiveCorrectCount;
        this.sixCorrectCount = sixCorrectCount;
    }


    public Integer getThreeCorrectCount() {
        return threeCorrectCount;
    }

    public Integer getFourCorrectCount() {
        return fourCorrectCount;
    }

    public Integer getFiveCorrectCount() {
        return fiveCorrectCount;
    }

    public Integer getSixCorrectCount() {
        return sixCorrectCount;
    }
}
