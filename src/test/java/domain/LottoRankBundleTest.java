package domain;

import FakeRandomNumber.FakeLottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoRankBundleTest {

    @Test
    @DisplayName("로또 랭크 반환 메서드 사이즈 테스트")
    void getLottoRank() {
        // given
        final CreateLottoNumber createLottoNumber = new FakeLottoNumberGenerator();
        final int lottoMoney = 3000;
        final String passiveLottoNumber = "1,2,3,4,5,11";
        final PassiveLottoNumber passiveLottoNumbers = new PassiveLottoNumber(List.of(passiveLottoNumber));
        final Lottos lottos = new Lottos(createLottoNumber, passiveLottoNumbers.getPassiveLottoNumbers(), lottoMoney);
        final List<Integer> lastWeekLottoNumber = new ArrayList<>(List.of(2, 3, 4, 5, 6, 7, 11));
        final LottoRankBundle lottoRankBundle = new LottoRankBundle(lottos, lastWeekLottoNumber);
        final List<LottoRank> expected = List.of(new LottoRank("3", 0), new LottoRank("4", 0), new LottoRank("5", 2), new LottoRank("5BONUS", 1), new LottoRank("6", 0));

        // when
        final List<domain.LottoRank> actual = lottoRankBundle.getLottoRank();


        // then
        assertEquals(expected.size(), actual.size());
    }
}
