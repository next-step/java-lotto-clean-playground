package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static model.LottoRank.MISS;


public class LottoResult {
    private final Map<LottoRank, Integer> lottoStatus = new HashMap<>();

    public LottoResult() {
        initLottoStatus();
    }

    private void initLottoStatus() {
        for (LottoRank lottoRank : LottoRank.values()) {
            lottoStatus.put(lottoRank, 0);
        }
    }

    public void calculate(List<LottoTicket> tickets, List<Integer> winningNumbers, int bonusNumber) {
        for (LottoTicket ticket : tickets) {
            int matchCount = ticket.calculateMatch(winningNumbers);
            LottoRank lottoRank = LottoRank.find(matchCount);
            if (matchCount == 5 && ticket.hasBonusBall(bonusNumber)) {
                lottoStatus.put(LottoRank.FIVE_BONUS, lottoStatus.get(lottoRank) + 1);
                continue;
            }
            if (lottoRank != MISS) {
                lottoStatus.put(lottoRank, lottoStatus.get(lottoRank) + 1);
            }

        }
    }

    public int getWinningAmount(Map<LottoRank, Integer> status) {
        int total = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            int count = status.getOrDefault(lottoRank, 0);
            if (lottoRank == MISS) {
                return total;
            }
            total += lottoRank.getWinningMoney() * count;
        }
        return total;
    }

    public Map<LottoRank, Integer> getResult() {
        return lottoStatus;
    }
}
