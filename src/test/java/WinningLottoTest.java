import static model.LottoType.AUTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import model.Lotto;
import model.LottoNumber;
import model.Rank;
import model.WinningLotto;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void 일치하는_등수_반환_보너스는_틀린경우() {
        Lotto winning = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        ),AUTO);
        LottoNumber bonus = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winning, bonus);

        Lotto matchLotto = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        ),AUTO);

        Rank rank = winningLotto.match(matchLotto);
        assertEquals(Rank.SIX_MATCH, rank);
    }

    @Test
    void 당첨번호_5개일치_보너스_맞은경우_2등이어야함() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(
                        LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                        LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                        LottoNumber.valueOf(5), LottoNumber.valueOf(6)
                ),AUTO),
                LottoNumber.valueOf(7)
        );

        Lotto lotto = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2),
                LottoNumber.valueOf(3), LottoNumber.valueOf(4),
                LottoNumber.valueOf(5), LottoNumber.valueOf(7)
        ),AUTO);

        assertEquals(Rank.FIVE_MATCH_BONUS, winningLotto.match(lotto));
    }
}
