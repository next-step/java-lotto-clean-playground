package domain;

import java.util.Arrays;
import java.util.function.Function;

public enum LottoWinningType {
    FIRST_PLACE("6개 일치 (2000000000원)- ", tickets -> tickets * 2000000000),
    SECOND_PLACE("5개 일치, 보너스 볼 일치(30000000원)- ", tickets -> tickets * 30000000),
    THIRD_PLACE("5개 일치 (1500000원)- ", tickets -> tickets * 1500000),
    FOURTH_PLACE("4개 일치 (50000원)- ", tickets -> tickets * 50000),
    FIFTH_PLACE("3개 일치 (5000원)- ", tickets -> tickets * 5000),
    NO_PRIZE("2개 이하 일치 (0원)- ", tickets -> 0d);

    private String winningDescription;
    private Function<Double, Double> prizeCalculator;


    LottoWinningType(String winningDescription, Function<Double, Double> prizeCalculator) {
        this.winningDescription = winningDescription;
        this.prizeCalculator = prizeCalculator;

    }

    public double calculatePrize(double winningTicketCount) {
        return prizeCalculator.apply(winningTicketCount);
    }

    public String getWinningDescription() {
        return winningDescription;
    }

    public static LottoWinningType of(int matchCount, boolean matchBonus) {
        if (matchCount == 6) return FIRST_PLACE;
        if (matchCount == 5 && matchBonus) return SECOND_PLACE;
        if (matchCount == 5) return THIRD_PLACE;
        if (matchCount == 4) return FOURTH_PLACE;
        if (matchCount == 3) return FIFTH_PLACE;
        return NO_PRIZE;
    }

    public static LottoWinningType findLottoWinningType(String winningType){
        return Arrays.stream(LottoWinningType.values())
                .filter(lottoWinningTypePrize -> lottoWinningTypePrize.name().equals(winningType))
                .findAny()
                .orElse(NO_PRIZE);
    }

}
