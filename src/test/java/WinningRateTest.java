import domain.Rank;
import domain.WinningRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinningRateTest {
    @Test
    @DisplayName("1등이 두 장이면(40억이 넘어도) 예외가 일어나지 않는다.")
    void firstRankTwo() {
        List<Rank> ranks = new ArrayList<>();

        ranks.add(Rank.FIRST);
        ranks.add(Rank.FIRST);

        WinningRate winningRate = new WinningRate();
        long result = winningRate.calculateWinPrice(ranks);

        assertEquals(4000000000L, result);
    }
}
