package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoRankEvaluator {

    public static List<LottoRank> evaluate(Lottos lottos, Lotto winningNumbers, LottoNumber bonusNumber) {
        List<Lotto> lottoList = lottos.getLottos();

        if (winningNumbers.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }

        return lottoList.stream()
                .map(lotto ->
                        LottoRank.matchRank(lotto.matchNumbers(winningNumbers),
                        lotto.hasBonusNumber(bonusNumber)))
                .collect(Collectors.toList());
    }

}
