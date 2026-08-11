package domain;

import java.util.ArrayList;

public class LottoChecker {

    private final ArrayList<Integer> winningLottoNumbers;
    private final LottoTickets lottoTickets;

    private final int bonusNumber;

    public LottoChecker(Lotto winningLotto, LottoTickets lottoTickets, String bonusNumber) {
        this.winningLottoNumbers = new ArrayList<>(winningLotto.getNumbers());
        this.lottoTickets = lottoTickets;
        validateBonusNumber(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    public ArrayList<LottoWinningType> checkAllTickets() {
        ArrayList<LottoWinningType> winningTypes = new ArrayList<>();

        for (int i = 0; i < lottoTickets.getSize(); i++) {
            int matchCount = calculateMatchCountForTicket(i);
            boolean matchBonus = hasBonusNumber(i);

            winningTypes.add(LottoWinningType.of(matchCount, matchBonus));
        }
        return winningTypes;
    }

    public boolean hasBonusNumber(int lottoTicketIndex) {
        return lottoTickets.getTicketNumbers(lottoTicketIndex).contains(this.bonusNumber);
    }

    private void validateBonusNumber(String bonusNumber) {
        try {
            int number = Integer.parseInt(bonusNumber);
            if (number < Lotto.LOTTO_NUMBER_LOWER_BOUND || number > Lotto.LOTTO_NUMBER_BOUND) {
                throw new IllegalArgumentException(
                        "보너스 볼은" + Lotto.LOTTO_NUMBER_LOWER_BOUND + "과" + Lotto.LOTTO_NUMBER_BOUND + "사이의 숫자여야 합니다.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼은 숫자여야 합니다.");
        }
    }

    private int calculateMatchCountForTicket(int lottoTicketIndex) {
        int matchCount = 0;
        for (int winningNumber : winningLottoNumbers) {
            matchCount += getMatchScore(lottoTicketIndex, winningNumber);
        }
        return matchCount;
    }

    private int getMatchScore(int lottoTicketIndex, int winningNumber) {
        if (lottoTickets.getTicketNumbers(lottoTicketIndex).contains(winningNumber)) {
            return 1;
        }
        return 0;
    }

}
