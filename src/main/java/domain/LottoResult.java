package domain;

public record LottoResult(
        int firstPrizeCount,
        int secondPrizeCount,
        int thirdPrizeCount,
        int fourthPrizeCount
) {}
