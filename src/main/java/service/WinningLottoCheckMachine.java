package service;

import model.*;

import java.util.HashMap;
import java.util.Map;

public class WinningLottoCheckMachine {

    private final LottoTicket winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLottoCheckMachine(LottoTicket winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;

        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;

    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public LottoResult operateLottoCheckMachine(LottoTickets lottoTickets, int lottoPurchaseAmount){

        Map<LottoRank, Integer> winningResult = initializeWinningResultMap();

        winningResult = checkWinningTicket(lottoTickets,winningResult);

        int totalPrize = LottoCalculator.calculateTotalPrize(winningResult);
        double earningRate = LottoCalculator.calculateEarningRate(totalPrize,lottoPurchaseAmount);

        return new LottoResult(winningResult, earningRate);

    }

    private static Map<LottoRank, Integer> initializeWinningResultMap() {
        Map<LottoRank, Integer> result = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    private Map<LottoRank, Integer> checkWinningTicket(LottoTickets lottoTickets, Map<LottoRank, Integer> winningResult){
        for (LottoTicket ticket : lottoTickets.getTickets()) {

            int matchCount = ticket.countMatchingNumbers(winningNumbers);
            boolean isMatchBonusBall = ticket.contains(bonusNumber);

            if (doesMatchWithBonusNumberExcept2ndRank(isMatchBonusBall, matchCount)) matchCount++;

            LottoRank rank = LottoRank.valueOf(matchCount,isMatchBonusBall);

            winningResult.put(rank,winningResult.get(rank)+1);
        }
        
        return winningResult;
    }

    private static boolean doesMatchWithBonusNumberExcept2ndRank(boolean isMatchBonusBall, int matchCount) {
        return isMatchBonusBall && matchCount != 5;
    }

}
