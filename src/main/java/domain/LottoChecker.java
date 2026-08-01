package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoChecker {
    private final ArrayList<Integer> winningLottoNumbers;
    private final LottoTickets lottoTickets;

    public LottoChecker(String[] lastWeekWinnerLottoNumbers, LottoTickets lottoTickets) {
        this.winningLottoNumbers = wrappingToIntegerLottoNumbers(lastWeekWinnerLottoNumbers);
        this.lottoTickets = lottoTickets;
    }

    private ArrayList<Integer> wrappingToIntegerLottoNumbers(String[] stringWinnerNumbers) {
        return (ArrayList<Integer>) Arrays.stream(stringWinnerNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public ArrayList<Integer> checkAllTickets() {
        ArrayList<Integer> matchCounts = new ArrayList<>();

        for (int i = 0; i < lottoTickets.getSize(); i++) {
            matchCounts.add(calculateMatchCountForTicket(i));
        }

        return matchCounts;
    }

    private int calculateMatchCountForTicket(int lottoTicketIndex) {
        int matchCount = 0;

        for (int winningNumber : winningLottoNumbers) {
            matchCount += getMatchScore(lottoTicketIndex, winningNumber);
        }

        return matchCount;
    }

    private int getMatchScore(int lottoTicketIndex, int winningNumber) {
        if (lottoTickets.getLottoTreeSet(lottoTicketIndex).contains(winningNumber)) {
            return 1;
        }
        return 0;
    }

    public Map<Integer, Integer> countMatches(ArrayList<Integer> matchCounts) {
        Map<Integer, Integer> matchStatistics = new HashMap<>();

        for (int matchCount : matchCounts) {
            updateStatistics(matchStatistics, matchCount);
        }

        return matchStatistics;
    }

    private void updateStatistics(Map<Integer, Integer> matchStatistics, int matchCount) {
        if (matchCount < 3) {
            return;
        }

        if (matchStatistics.containsKey(matchCount)) {
            int currentCount = matchStatistics.get(matchCount);
            matchStatistics.put(matchCount, currentCount + 1);
            return;
        }

        matchStatistics.put(matchCount, 1);
    }


}
