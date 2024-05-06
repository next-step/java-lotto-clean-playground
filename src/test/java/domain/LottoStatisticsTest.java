package domain;

import model.Lotto;
import model.LottoStatistics;
import model.Rating;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoStatisticsTest {
    private LottoStatistics lottoStatistics = new LottoStatistics("1, 2, 3, 4, 5, 6");

    private List<Lotto> haveLottos;
    private Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    private Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 40, 45));
    private Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 5, 7));

    public LottoStatisticsTest() {

        lottoStatistics.initBonusBall("7");
        haveLottos = new ArrayList<>();
    }

    private void lottoUpdate() {

        lotto1.updateCollectedCount(6);
        lotto2.updateCollectedCount(4);
        lotto3.updateCollectedCount(5);
        lotto3.updateBonusCorrect();
    }

    private void addHaveLottos() {

        haveLottos.add(lotto1);
        haveLottos.add(lotto2);
        haveLottos.add(lotto3);
    }

    @Test
    void 로또번호를_맞춘갯수의_ratingInfo의_키값은_하나_증가한다() {

        lottoUpdate();
        addHaveLottos();

        lottoStatistics.configureMatchedCount(haveLottos);

        assertEquals(1, lottoStatistics.getRatingInfo().getCount(Rating.FIRST));
        assertEquals(1, lottoStatistics.getRatingInfo().getCount(Rating.FOURTH));
        assertEquals(1, lottoStatistics.getRatingInfo().getCount(Rating.SECOND));
    }

    @Test
    void 수익율을_계산한다() {

        lottoUpdate();
        addHaveLottos();

        lottoStatistics.configureMatchedCount(haveLottos);

        double actual = lottoStatistics.calculateRatetoReturn(3000);
        double expected = (2000000000.0 + 30000000.0 + 50000.0) / 3000.0;

        assertEquals(expected, actual);
    }
}
