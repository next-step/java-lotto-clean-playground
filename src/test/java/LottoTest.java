import domain.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {

    @Test
    void 가격에_맞는_로또_개수_테스트() {
        final LottoMarket lottoMarket = new LottoMarket(14000, 0);
        final Lottos lottos = lottoMarket.generateTickets();
        int expected = 14;

        int actual = lottos.getLottos().size();

        assertEquals(expected, actual);
    }

    @Test
    void 자동_로또_개수_테스트() {
        final LottoMarket lottoMarket = new LottoMarket(14000, 5);
        final Lottos lottos = lottoMarket.generateTickets();
        int expected = 9;

        int actual = lottos.getLottos().size();

        assertEquals(expected, actual);
    }


    @Test
    void 로또_한개_사이즈_테스트() {
        final LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> LottoList = lottoNumbers.generateLottoNumber();
        final int expected = 6;

        int actual = LottoList.size();

        assertEquals(expected, actual);
    }

    @Test
    void 로또_번호_중복_테스트() {
        final LottoNumbers lottoNumbers = new LottoNumbers();
        List<Integer> LottoList = lottoNumbers.generateLottoNumber();

        Set<Integer> numberSet = new HashSet<>(LottoList);

        assertEquals(6, numberSet.size());
    }

    @Test
    void 범위가_잘못된_로또_번호_입력_테스트() {
        String invalidWinNumber = "1,3,5,7,9,50"; // 50은 잘못된 번호
        assertThrows(IllegalArgumentException.class, () -> {
            new LottoNumberList(invalidWinNumber);
        }, "잘못된 번호가 입력되었습니다.");
    }

    @Test
    void 잘못된_당첨번호_입력_테스트() {
        final String winNumber = "1,3,5,7,9,A";

        assertThrows(NumberFormatException.class, () ->
                new LottoNumberList(winNumber).getNumbers());
    }

    @Test
    void 수동_개수_잘못된_입력_테스트() {
        final LottoMarket lottoMarket = new LottoMarket(14000, 15);

        assertThrows(IllegalArgumentException.class, () -> {
            lottoMarket.generateTickets();
        }, "잘못된 입력입니다.");
    }
}
