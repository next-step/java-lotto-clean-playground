package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fixture.LottoFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoGroupTest {

    private final List<Lotto> lottos = List.of(testLottoOneToSix, testLottoSevenToTwelve, testLottoFortyToFortyFive);

    @Test
    @DisplayName("OK : 로또들의 번호 목록들을 조회한다.")
    void getAllLottoNumbersList() {
        LottoGroup lottoGroup = new LottoGroup(lottos);
        List<List<LottoNumber>> expectResult = List.of(testLottoNumbersOneToSix, testLottoNumbersSevenToTwelve, testLottoNumbersFortyToFortyFive);

        assertThat(lottoGroup.getAllLottoNumbersList()).isEqualTo(expectResult);
    }

    @Test
    @DisplayName("OK : 두 로또 그룹을 병합한다.")
    void combineLottoGroup() {
        LottoGroup lottoGroupOne = new LottoGroup(List.of(testLottoOneToSix));
        LottoGroup lottoGroupTwo = new LottoGroup(List.of(testLottoSevenToTwelve, testLottoFortyToFortyFive));
        LottoGroup combinedLottoGroup = LottoGroup.combineLottoGroup(lottoGroupOne, lottoGroupTwo);

        assertThat(combinedLottoGroup.getLottos()).isEqualTo(lottos);
    }
}