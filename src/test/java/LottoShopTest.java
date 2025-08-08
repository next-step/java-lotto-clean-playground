import model.LottoShop;
import model.LottoTickets;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class LottoShopTest {
    @Test
    void 수동_로또와_자동_로또가_개수에_맞게_생성된다() {
        int amount = 5000;
        List<List<Integer>> manual = List.of(
                List.of(1,2,3,4,5,6),
                List.of(7,8,9,10,11,12)
        );
        LottoShop shop = new LottoShop(amount, manual);
        LottoTickets tickets = shop.getTickets();
        assertEquals(2, tickets.getManualLottos().size());
        assertEquals(3, tickets.getRandomLottos().size());
        assertEquals(5, tickets.getAllLottos().size());
    }

    @Test
    void 로또_금액은_1000원_단위가_아니면_예외가_발생한다() {
        List<List<Integer>> manual = List.of();
        assertThrows(IllegalArgumentException.class,
                () -> new LottoShop(1500, manual)
        );
    }

    @Test
    void 로또_구매_금액보다_수동_로또_개수가_많으면_예외가_발생한다() {
        int amount = 1000;
        List<List<Integer>> manual = List.of(
                List.of(1,2,3,4,5,6),
                List.of(7,8,9,10,11,12)
        );
        assertThrows(IllegalArgumentException.class,
                () -> new LottoShop(amount, manual)
        );
    }
}
