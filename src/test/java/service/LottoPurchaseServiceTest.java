package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import dto.LottoPurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseServiceTest {

    private LottoGenerator lottoGenerator;
    private LottoPurchaseService lottoPurchaseService;

    @BeforeEach
    void setUp() {
        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        lottoGenerator = new LottoGenerator(numberGenerator);
        lottoPurchaseService = new LottoPurchaseService(lottoGenerator);
    }

    @Test
    @DisplayName("수동 로또 2개와 자동 로또 3개가 포함된 총 5개의 로또가 정확히 생성되어야 한다")
    void purchase_withManualAndAutoLottos_shouldReturnCorrectLottos() {
        // given
        List<Lotto> manualLottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6))),
                new Lotto(List.of(new LottoNumber(7), new LottoNumber(8), new LottoNumber(9), new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)))
        );
        int purchaseAmount = 10000;
        int manualCount = 2;
        int autoCount = (purchaseAmount / Lotto.PRICE) - manualCount;

        // when
        Lottos result = lottoPurchaseService.purchase(new LottoPurchaseDto(purchaseAmount, manualCount, new Lottos(manualLottos), autoCount));

        // then
        assertThat(result.count()).isEqualTo(manualLottos.size() + autoCount);
    }

    @Test
    @DisplayName("수동 로또가 없고 자동 로또만 생성되어야 하며, 총 5개의 자동 로또가 생성되어야 한다")
    void purchase_withNoManualLottos_shouldReturnOnlyAutoLottos() {
        // given
        List<Lotto> manualLottos = List.of();
        int purchaseAmount = 5000;
        int manualCount = 0;
        int autoCount = purchaseAmount / Lotto.PRICE;

        // when
        Lottos result = lottoPurchaseService.purchase(new LottoPurchaseDto(purchaseAmount, manualCount, new Lottos(manualLottos), autoCount));

        // then
        assertThat(result.count()).isEqualTo(autoCount);
    }

    @Test
    @DisplayName("자동 로또 없이 수동 로또만 정확히 생성되어야 하며, 총 5개의 수동 로또가 생성되어야 한다")
    void purchase_withOnlyManualLottos_shouldReturnOnlyManualLottos() {
        // given
        List<Lotto> manualLottos = List.of(
                new Lotto(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)))
        );
        int purchaseAmount = 5000;
        int manualCount = 5;
        int autoCount = 0;

        // when
        Lottos result = lottoPurchaseService.purchase(new LottoPurchaseDto(purchaseAmount, manualCount, new Lottos(manualLottos), autoCount));

        // then
        assertThat(result.count()).isEqualTo(manualLottos.size());
    }
}
