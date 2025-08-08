import model.Lotto;
import model.Rank;
import model.StaticsResult;
import model.WinningLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticsResultTest {

    @Test
    void 등수별로_개수를_검증한다() {
        var tickets = List.of(
                new Lotto(List.of(1,2,3,4,5,6)), // 1등
                new Lotto(List.of(1,2,3,4,5,7)), // 2등(5개+보너스)
                new Lotto(List.of(1,2,3,4,5,8)), // 3등(5개)
                new Lotto(List.of(1,2,3,4,9,10)), // 4등(4개)
                new Lotto(List.of(1,2,3,11,12,13)) // 5등(3개)
        );

        var winning = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        var stats = new StaticsResult().calculate(tickets, winning);

        assertEquals(1, stats.get(Rank.FIRST));
        assertEquals(1, stats.get(Rank.SECOND));
        assertEquals(1, stats.get(Rank.THIRD));
        assertEquals(1, stats.get(Rank.FOURTH));
        assertEquals(1, stats.get(Rank.THIRD));
    }
}
