package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoChecker {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_BOUND = 45;

    private final ArrayList<Integer> winningLottoNumbers;
    private final LottoTickets lottoTickets;

    private final int bonusNumber;

    public LottoChecker(String[] lastWeekWinnerLottoNumbers, LottoTickets lottoTickets, String bonusNumber) {
        this.winningLottoNumbers = wrappingToIntegerLottoNumbers(lastWeekWinnerLottoNumbers);
        this.lottoTickets = lottoTickets;
        validateBonusNumber(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    private void validateBonusNumber(String bonusNumber) {
        try {
            int number = Integer.parseInt(bonusNumber);
            if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_BOUND) {
                throw new IllegalArgumentException("보너스 볼은" + LOTTO_NUMBER_LOWER_BOUND + "과" + LOTTO_NUMBER_BOUND + "사이의 숫자여야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼은 숫자여야 합니다.");
        }
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
