package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoChecker {
    private final ArrayList<Integer> winningLottoNumbers;
    private final LottoTickets lottoTickets;

    private final int bonusNumber;

    public LottoChecker(String[] lastWeekWinnerLottoNumbers, LottoTickets lottoTickets, String bonusNumber) {
        this.winningLottoNumbers = wrappingToIntegerLottoNumbers(lastWeekWinnerLottoNumbers);
        this.lottoTickets = lottoTickets;

        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    private ArrayList<Integer> wrappingToIntegerLottoNumbers(String[] stringWinnerNumbers) {
        return (ArrayList<Integer>) Arrays.stream(stringWinnerNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public ArrayList<LottoWinningType> checkAllTickets() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>();

        for (int i = 0; i < lottoTickets.getSize(); i++) {
            int matchCount = calculateMatchCountForTicket(i);
            boolean matchBonus = hasBonusNumber(i);

            winningTypes.add(LottoWinningType.valueOf(matchCount, matchBonus));
        }
        return winningTypes;
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

    public boolean hasBonusNumber(int lottoTicketIndex) {
        return lottoTickets.getLottoTreeSet(lottoTicketIndex).contains(this.bonusNumber);
    }
}
