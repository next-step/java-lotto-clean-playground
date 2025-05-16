package service.purchase;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Lottos;
import dto.LottoPurchaseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoGenerator;
import service.LottoPurchaseService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoPurchaseServiceImplTest {

    @Test
    @DisplayName("구매 금액과 수동 로또 수에 따라 자동 로또를 생성하고 병합한다")
    void purchaseLottos_correctlyGeneratesAutoAndManualLottos() {
        // given
        LottoGenerator fixedGenerator = createFixedGenerator();
        LottoPurchaseService service = new LottoPurchaseServiceImpl(fixedGenerator);

        int manualCount = 2;
        int purchaseAmount = 5000;
        List<Lotto> manualLottos = createManualLottos();

        LottoPurchaseDto dto = new LottoPurchaseDto(purchaseAmount, manualCount, new Lottos(manualLottos));

        // when
        Lottos result = service.purchase(dto);

        //then
        assertThat(result.count()).isEqualTo(5);
    }

    private LottoGenerator createFixedGenerator() {
        return new LottoGenerator() {
            @Override
            public Lottos generate(int amount) {
                throw new UnsupportedOperationException("generate(amount)는 테스트에서 사용하지 않습니다.");
            }

            @Override
            public Lottos generateByCount(int count) {
                List<Lotto> tickets = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    LottoNumbers numbers = new LottoNumbers(List.of(
                            new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                            new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                    ));
                    tickets.add(new Lotto(numbers));
                }
                return new Lottos(tickets);
            }
        };
    }

    private List<Lotto> createManualLottos() {
        return List.of(
                new Lotto(new LottoNumbers(List.of(
                        new LottoNumber(1), new LottoNumber(3), new LottoNumber(5),
                        new LottoNumber(7), new LottoNumber(9), new LottoNumber(11)
                ))),
                new Lotto(new LottoNumbers(List.of(
                        new LottoNumber(2), new LottoNumber(4), new LottoNumber(6),
                        new LottoNumber(8), new LottoNumber(10), new LottoNumber(12)
                )))
        );
    }
}
