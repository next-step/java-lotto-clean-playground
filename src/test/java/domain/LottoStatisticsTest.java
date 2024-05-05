package domain;

import model.Lotto;
import model.LottoStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("로또 통계 테스트")
class LottoStatisticsTest {

    private LottoStatistics lottoStatistics = new LottoStatistics(Arrays.asList(1, 2, 3, 4, 5, 6));

    @Test
    void 당첨번호를_6개_입력_안할경우_오류가_발생한다() {

        List<Integer> collectNumber = Arrays.asList(1, 2, 3);

        assertThatThrownBy(() -> new LottoStatistics(collectNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 6개의 당첨번호를 입력하세요");
    }

    @Test
    void 당첨번호가_중복되면_오류가_발생한다() {

        List<Integer> collectNumber = Arrays.asList(1, 1, 3, 4, 5, 6);

        assertThatThrownBy(() -> new LottoStatistics(collectNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 서로 다른 숫자만 입력하세요");
    }

    @Test
    void 당첨번호가_1부터_45안에_숫자가_아니면_오류가_발생한다() {

        List<Integer> collectNumber = Arrays.asList(0, 46, 1, 2, 3, 4);

        assertThatThrownBy(() -> new LottoStatistics(collectNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1에서 45까지의 숫자만 입력하세요");

    }

    @Test
    void mactedCount배열에는_당첨된_로또의_갯수가_들어간다() {
        List<Lotto> haveLottos = Arrays.asList(
                new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Lotto(Arrays.asList(1, 2, 3, 42, 43, 45)),
                new Lotto(Arrays.asList(1, 2, 3, 4, 10, 51))
        );

        haveLottos.get(0).setCollectedCount(6);
        haveLottos.get(1).setCollectedCount(3);
        haveLottos.get(2).setCollectedCount(4);

        lottoStatistics.configureMatchedCount(haveLottos);

        assertEquals(1, lottoStatistics.getMatchedCount().get(3));
        assertEquals(1, lottoStatistics.getMatchedCount().get(4));
        assertEquals(0, lottoStatistics.getMatchedCount().get(5));
        assertEquals(1, lottoStatistics.getMatchedCount().get(6));
    }
}