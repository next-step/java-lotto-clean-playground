package service;

import domain.*;
import dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.FixNumbersGenerator;

import java.util.List;

import static constant.LottoConstant.LOTTO_PRICE;
import static fixture.LottoFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new LottoService(new LottoStore(new FixNumbersGenerator(testNumbersOneToSix)));
    }

    @Test
    @DisplayName("OK : 로또 횟수를 응답받는다.")
    void getLottoCount() {
        long expectLottoCount = 1L;
        GetLottoCountResponse response = lottoService.getLottoCount(1000L);

        assertThat(response.lottoCount()).isEqualTo(expectLottoCount);
    }

    @Test
    @DisplayName("OK : 로또를 구매하여 로또 그룹을 응답받는다.")
    void purchaseLottos() {
        List<Lotto> lottos = List.of(testLottoOneToSix, testLottoOneToSix, testLottoOneToSix);
        LottoGroup expectLottoGroup = new LottoGroup(lottos);
        PurchaseLottosResponse response = lottoService.purchaseLottos(List.of(testLottoOneToSix), 2);

        assertThat(response.lottoGroup().getAllLottoNumbersList()).isEqualTo(expectLottoGroup.getAllLottoNumbersList());
    }

    @Test
    @DisplayName("OK : 로또를 진행하여 결과를 응답받는다.")
    void playLottoGame() {
        List<Lotto> lottos = List.of(testLottoOneToSix, testLottoSevenToTwelve, testLottoFortyToFortyFive);
        LottoGroup lottoGroup = new LottoGroup(lottos);
        PlayLottoGameResponse response = lottoService.playLottoGame(lottoGroup, WinLotto.of(testLottoOneToSix, new LottoNumber(7)));

        assertThat(response.lottoRankResultDTOS()).isEqualTo(getLottoRankResults());
    }

    @Test
    @DisplayName("OK : 수익률을 응답받는다.")
    void calculateEarningsRate() {
        double expectEarningRate = (double) LottoRank.FIRST_PLACE.getPrize() / (LOTTO_PRICE * 3);
        CalculateEarningRateResponse response = lottoService.calculateEarningsRate(getLottoRankResults());

        assertThat(response.earningRate()).isEqualTo(expectEarningRate);
    }

    private List<LottoRankResultDTO> getLottoRankResults() {
        LottoRankResultDTO first = LottoRankResultDTO.of(LottoRank.FIRST_PLACE, 1);
        LottoRankResultDTO second = LottoRankResultDTO.of(LottoRank.SECOND_PLACE, 0);
        LottoRankResultDTO third = LottoRankResultDTO.of(LottoRank.THIRD_PLACE, 0);
        LottoRankResultDTO fourth = LottoRankResultDTO.of(LottoRank.FOURTH_PLACE, 0);
        LottoRankResultDTO fifth = LottoRankResultDTO.of(LottoRank.FIFTH_PLACE, 0);
        LottoRankResultDTO noPlace = LottoRankResultDTO.of(LottoRank.NO_PLACE, 2);

        return List.of(first, second, third, fourth, fifth, noPlace);
    }
}