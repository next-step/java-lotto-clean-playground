package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import dto.LottoPurchaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseServiceTest {

    private LottoPurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        purchaseService = new LottoPurchaseService(new FixedLottoGenerator());
    }

    @Test
    @DisplayName("구입 금액이 5,000원이고 수동 로또가 2장일 때, 자동 로또 3장이 생성되어 총 5장이 반환된다")
    void purchaseLottos_correctly() {
        // given
        int purchaseAmount = 5000;
        int manualCount = 2;
        Lottos manualLottos = createManualLottos();
        LottoPurchaseDto request = new LottoPurchaseDto(purchaseAmount, manualCount, manualLottos);

        // when
        Lottos result = purchaseService.purchase(request);

        // then
        assertThat(result.count()).isEqualTo(5);
        assertThat(result.getLottos().subList(0, 2)).isEqualTo(manualLottos.getLottos());
    }

    private Lottos createManualLottos() {
        return new Lottos(List.of(
                fixedLotto(List.of(1, 2, 3, 4, 5, 6)),
                fixedLotto(List.of(7, 8, 9, 10, 11, 12))
        ));
    }

    private Lotto fixedLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    static class FixedLottoGenerator extends LottoGenerator {
        public FixedLottoGenerator() {
            super(null);
        }

        @Override
        public Lottos generate(int count) {
            List<Lotto> tickets = IntStream.range(0, count)
                    .mapToObj(i -> new Lotto(
                            IntStream.rangeClosed(10, 15)
                                    .mapToObj(LottoNumber::new)
                                    .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());
            return new Lottos(tickets);
        }
    }
}
