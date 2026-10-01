package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("로또가 원하는 개수만큼 생성된다")
    void 로또가_원하는_개수만큼_생성된다() {
        List<Lotto> lottoList = Lottos.generate(6, new LottoNumberGenerator()).getLottoList();

        assertThat(lottoList).hasSize(6);
    }



    @Test
    @DisplayName("당청번호랑 몇개 일치하는지, 정확히 세는지")
    void 당첨번호랑_몇개_일치하는지_정확히_세는지() {
        WinningNumbers winningLotto = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(1, 2, 3, 7, 8, 9)), // 3개 일치
                new Lotto(List.of(14, 15, 16, 17, 18, 19)) // 일치 없음
        ));
        int matchCount1 = lottos.getLottoList().get(0).countMatch(winningLotto);
        int matchCount2 = lottos.getLottoList().get(1).countMatch(winningLotto);
        assertThat(matchCount1).isEqualTo(3);
        assertThat(matchCount2).isEqualTo(0);
    }


}
