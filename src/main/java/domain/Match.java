package domain;

public class Match {

    public static int getMatchCount(Lotto lotto, Lotto lottoAnswer) {
        int matchCount = 0;

        for (int number : lotto.getNumbers()) {
            if (lottoAnswer.getNumbers().contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
