package domain;

public class WinningResultDto {

    private int threeMatchCount;
    private int fourMatchCount;
    private int fiveMatchCount;
    private int sixMatchCount;

    public void addMatchCount(int matchCount) {
        switch (matchCount) {
            case 3 -> threeMatchCount++;
            case 4 -> fourMatchCount++;
            case 5 -> fiveMatchCount++;
            case 6 -> sixMatchCount++;
        }
    }

    public int getThreeMatchCount() { return threeMatchCount; }
    public int getFourMatchCount() { return fourMatchCount; }
    public int getFiveMatchCount() { return fiveMatchCount; }
    public int getSixMatchCount() { return sixMatchCount; }
}