package lotto.model;

import java.util.*;

public class ResultStatistics {
    private static int BONUSBALL_KEY = 7;
    private Map<Integer, Integer> statistics = new LinkedHashMap<>();
    private Map<Integer, Boolean> matchCountAndBonusBall = new HashMap<>();
    private double profitRate;

    public ResultStatistics() {
    }

    public void statisticsInit() {
        statistics.put(3, 0);
        statistics.put(4, 0);
        statistics.put(5, 0);
        statistics.put(BONUSBALL_KEY, 0);
        statistics.put(6, 0);
    }

    public void ticketMatchCount(List<Integer> ticket, List<Integer> successList, int bonusBall){
        int matchCount = 0;
        for (Integer num : ticket) {
            if (successList.contains(num)) {
                matchCount++;
            }
        }
        if(matchCount==5&&ticket.contains(bonusBall)&&!successList.contains(bonusBall)){
            matchCountAndBonusBall.put(matchCount, true);
            statistics.put(BONUSBALL_KEY, statistics.get(BONUSBALL_KEY)+ 1);
        }
        else if(3<=matchCount && matchCount<=6){
            matchCountAndBonusBall.put(matchCount, false);
            statistics.put(matchCount, statistics.get(matchCount)+ 1);
        }
    }

    public void ticketMatchRate(List<LottoTicket> tickets, List<Integer> successList, int bonusBall) {
        for (LottoTicket ticket : tickets) {
            List<Integer> ticketNumbers = ticket.getLottoTicket();
            ticketMatchCount(ticketNumbers,successList,bonusBall);
        }
    }

    public void calculateProfit(int purchasePrice) {
        int profit = 0;
        for (Integer matchNum : statistics.keySet()) {
            Integer price = TicketPrize.decisionPrice(matchNum);
            Integer count = statistics.get(matchNum);
            profit += price * count;
        }
        profitRate = (double) profit / purchasePrice;
    }

    public int getBonusballKey() {
        return BONUSBALL_KEY;
    }

    public Map<Integer, Integer> getStatistics() {
        return statistics;
    }

    public double getProfitRate() {
        return profitRate;
    }
}


