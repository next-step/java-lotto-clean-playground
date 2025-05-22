package domain.result;

public record LottoResult(
        int firstPrizeCount,
        int secondPrizeCount,
        int thirdPrizeCount,
        int fourthPrizeCount,
        int fifthPrizeCount
) {}
