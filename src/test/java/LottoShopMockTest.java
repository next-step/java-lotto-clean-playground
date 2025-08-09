import model.*;
import org.junit.jupiter.api.Test;
import util.NumberGenerator;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

class LottoShopMockTest {

    @Test
    void 기능_테스트_모킹버전() {

        NumberGenerator mockGen = mock(NumberGenerator.class);

        when(mockGen.generate())
                .thenReturn(List.of(8, 21, 23, 41, 42, 43))
                .thenReturn(List.of(3, 5, 11, 16, 32, 38))
                .thenReturn(List.of(7, 11, 16, 35, 36, 44))
                .thenReturn(List.of(1, 8, 11, 31, 41, 42))
                .thenReturn(List.of(13, 14, 16, 38, 42, 45))
                .thenReturn(List.of(7, 11, 30, 40, 42, 43))
                .thenReturn(List.of(2, 13, 22, 32, 38, 45));

        List<List<Integer>> manual = List.of(List.of(1,2,3,4,5,6));

        LottoShop shop = new LottoShop(8000, manual, mockGen);
        LottoTickets tickets = shop.getTickets();

        assertThat(tickets.getAllLottos()).hasSize(8);

        assertThat(tickets.getRandomLottos())
                .extracting(Lotto::getNumbers)
                .containsExactly(
                        List.of(8, 21, 23, 41, 42, 43),
                        List.of(3, 5, 11, 16, 32, 38),
                        List.of(7, 11, 16, 35, 36, 44),
                        List.of(1, 8, 11, 31, 41, 42),
                        List.of(13, 14, 16, 38, 42, 45),
                        List.of(7, 11, 30, 40, 42, 43),
                        List.of(2, 13, 22, 32, 38, 45)
                );

        WinningLotto winning = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        StaticsResult stats = new StaticsResult();
        var resultMap = stats.calculate(tickets.getAllLottos(), winning);
        
        assertThat(resultMap.get(Rank.FIRST)).isEqualTo(1L);
    }
}
