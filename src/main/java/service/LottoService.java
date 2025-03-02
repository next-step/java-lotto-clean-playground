package service;

import model.LottoMachine;
import model.LottoRank;
import model.LottoResult;
import model.Lotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoService {

    private static final int TICKET_PRICE = 1000;

    public List<Lotto> generateLottos(int purchaseAmount, List<String> manualLottoList) {
        return new LottoMachine().purchaseTickets(purchaseAmount, convertToLottos(manualLottoList));
    }

    private List<Lotto> convertToLottos(List<String> manualLottoList) {
        return manualLottoList.stream()
                .map(this::convertToSingleLotto)
                .toList();
    }

    private Lotto convertToSingleLotto(String lottoNumbersString) {
        List<Integer> lottoNumbers = Arrays.stream(lottoNumbersString.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        return new Lotto(lottoNumbers);
    }

    public List<LottoRank> calculateRank(LottoResult lottoResult, List<Lotto> lottos) {
        return lottoResult.calculateRank(lottos);
    }

    public List<String> convertLottoRanksToStrings(List<LottoRank> lottoRanks) {
        List<String> lottoRankStrings = new ArrayList<>();

        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NO_WINNER) continue;

            long count = getRankCount(lottoRanks, rank);
            String rankString = generateRankString(rank, count);
            lottoRankStrings.add(rankString);
        }

        return lottoRankStrings;
    }

    private long getRankCount(List<LottoRank> lottoRanks, LottoRank rank) {
        return lottoRanks.stream()
                .filter(lottoRank -> lottoRank == rank)
                .count();
    }

    private String generateRankString(LottoRank rank, long count) {
        return rank.rankToString() + " (" + rank.getPrice() + "원)- " + count + "개";
    }


    public String calculateEarningsRate(List<LottoRank> lottoRanks) {
        int totalEarnings = calculateTotalEarnings(lottoRanks);
        int totalSpent = lottoRanks.size() * TICKET_PRICE;
        double earningsRate = (double) totalEarnings / totalSpent;

        return formatEarningsResult(earningsRate);
    }

    public int calculateTotalEarnings(List<LottoRank> lottoRanks) {
        return lottoRanks.stream()
                .mapToInt(LottoRank::getPrice)
                .sum();
    }

    private String formatEarningsResult(double earningsRate) {
        String result = "손해";
        if (earningsRate > 1) {
            result = "이익";
        }

        return "총 수익률은 " + String.format("%.2f", earningsRate) +
                "입니다.(기준이 1이기 때문에 결과적으로 " + result + "라는 의미임)";
    }
}
