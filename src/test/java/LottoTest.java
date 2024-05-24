import domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {

    @Test
    void 가격에_맞는_로또_개수_테스트() {
        final LottoMarket lottoMarket = new LottoMarket(14000);
        final Lottos lottos = lottoMarket.generateTickets();
        int expected = 14;

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
}
