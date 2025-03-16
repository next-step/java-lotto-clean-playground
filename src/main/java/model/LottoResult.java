package model;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> matchCountMap = new EnumMap<>(Rank.class);

    public LottoResult(List<Lotto> tickets, WinningNumbers winningNumbers) {
        for (Lotto ticket : tickets) {
            Rank rank = determineRank(ticket, winningNumbers);
            matchCountMap.put(rank, matchCountMap.getOrDefault(rank, 0) + 1);
        }
    }

    private Rank determineRank(Lotto ticket, WinningNumbers winningNumbers) {
        int matchCount = countMatchingNumbers(ticket, winningNumbers);
        boolean hasBonus = hasBonusNumber(ticket, winningNumbers);

        return getRank(matchCount, hasBonus);
    }

    // 일치하는 숫자 개수 계산
    private int countMatchingNumbers(Lotto ticket, WinningNumbers winningNumbers) {
        return (int) ticket.getNumbers().stream()
                .filter(winningNumbers.getNumbers()::contains)
                .count();
    }

    // 보너스 번호 포함 여부 확인
    private boolean hasBonusNumber(Lotto ticket, WinningNumbers winningNumbers) {
        return ticket.getNumbers().contains(winningNumbers.getBonusNumber());
    }

    private Rank getRank(int matchCount, boolean hasBonus) {
        if (matchCount == 6) return Rank.FIRST;
        if (matchCount == 5 && hasBonus) return Rank.SECOND;
        if (matchCount == 5) return Rank.THIRD;
        if (matchCount == 4) return Rank.FOURTH;
        if (matchCount == 3) return Rank.FIFTH;
        return Rank.NONE;
    }

    public Map<Rank, Integer> getMatchCountMap() {
        return matchCountMap;
    }

    public double calculateProfitRate(int totalCost) {
        List<Integer> prizeAmounts = convertToPrizeAmounts();
        int totalPrize = sumPrizeAmounts(prizeAmounts);
        return (double) totalPrize / totalCost;
    }

    // 데이터 변환
    private List<Integer> convertToPrizeAmounts() {
        return matchCountMap.entrySet().stream()
                .map(this::convertToPrizeAmount)
                .toList();
    }

    // Rank 데이터를 변환
    private int convertToPrizeAmount(Map.Entry<Rank, Integer> rankEntry) {
        Rank rank = rankEntry.getKey();
        int count = rankEntry.getValue();
        return rank.getPrizeMoney() * count;
    }

    // 변환된 데이터를 계산
    private int sumPrizeAmounts(List<Integer> prizeAmounts) {
        return prizeAmounts.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getFormattedWinningDetails() {
        Map<String, Integer> formattedDetails = new LinkedHashMap<>();

        for (Rank rank : Rank.values()) {
            addRankIfValid(formattedDetails, rank);
        }

        return formattedDetails;
    }

    private void addRankIfValid(Map<String, Integer> formattedDetails, Rank rank) {
        if (rank != Rank.NONE) {
            formattedDetails.put(rank.getDescription(), matchCountMap.getOrDefault(rank, 0));
        }
    }
}
