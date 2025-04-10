package domain;


import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LottoRank {
    FIFTH_PRIZE(3, false, 5000, (matchCount, hasBonus) -> matchCount == 3),
    FOURTH_PRIZE(4, false, 50000, (matchCount, hasBonus) -> matchCount == 4),
    THIRD_PRIZE(5, false, 150000, (matchCount, hasBonus) -> matchCount == 5 && !hasBonus),
    SECOND_PRIZE(5, true, 30000000, (matchCount, hasBonus) -> matchCount == 5 && hasBonus),
    FIRST_PRIZE(6, false, 2000000000, (matchCount, hasBonus) -> matchCount == 6),
    NO_PRIZE(0, false, 0, (matchCount, hasBonus) -> false);

    final boolean hasBonusNumber;
    final int matchNumberCount;
    final long prize;
    final BiPredicate<Integer, Boolean> checkRank;

    LottoRank(int matchNumberCount, boolean hasBonusNumber, long prize,
              BiPredicate<Integer, Boolean> checkRank) {
        this.matchNumberCount = matchNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.checkRank = checkRank;
    }
    
    public static LottoRank matchRank(int matchCount, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.checkRank.test(matchCount, hasBonus))
                .findFirst()
                .orElse(NO_PRIZE);
    }

    public long getPrize() {
        return prize;
    }

    public static List<LottoRank> ranks() {
        return Stream.of(LottoRank.values())
                .filter(lottoRank -> lottoRank != LottoRank.NO_PRIZE)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(matchNumberCount).append("개 일치");
        if (matchNumberCount == 5 && hasBonusNumber) {
            builder.append(", 보너스 볼 일치");
        }
        builder.append(" (").append(prize).append("원) - ");
        return builder.toString();
    }

}
