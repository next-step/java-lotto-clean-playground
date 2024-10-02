package service;

import model.LottoRank;
import model.LottoTicket;

import java.util.Map;

public class LottoCalculator {

    public static int calculateTotalLottoTicketCount(int lottoPurchaseAmount) {
        return lottoPurchaseAmount / LottoTicket.LOTTO_TICKET_PRICE;
    }

    public static int calculateAutomaticLottoTicketCount(int lottoTicketCount, int manualLottoTicketCount) {
        return lottoTicketCount-manualLottoTicketCount;
    }

    public static int calculateTotalPrize(Map<LottoRank, Integer> result) {

        int  totalPrize = 0;

        for (Map.Entry<LottoRank,Integer> entry : result.entrySet()) {
            totalPrize += entry.getKey().getPrize() * entry.getValue();
        }

        return totalPrize;
    }

    public static double calculateEarningRate(int totalPrize, int purchaseAmount) {
        return (double) totalPrize / purchaseAmount;
    }

}
