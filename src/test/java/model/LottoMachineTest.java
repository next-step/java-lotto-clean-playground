package model;

import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {
    private LottoMachine lottoMachine;
    private List<Lotto> manualLottos;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
        manualLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );
    }

    @Test
    @DisplayName("자동 로또만 구매하는 경우를 검증한다")
    void should_Purchase_Auto_Lottos() {

        int money = 5000;
        List<Lotto> manualLottos = List.of();

        List<Lotto> purchasedLottos = lottoMachine.purchaseTickets(money, manualLottos);

        assertThat(purchasedLottos)
                .hasSize(5)
                .allMatch(lotto -> lotto.getLottoNumbers().size() == 6);
    }

    @Test
    @DisplayName("수동 및 자동 로또 혼합 구매를 검증한다")
    void should_Purchase_Mixed_Lottos() {

        int money = 5000;

        List<Lotto> purchasedLottos = lottoMachine.purchaseTickets(money, manualLottos);

        assertThat(purchasedLottos)
                .hasSize(5)
                .contains(manualLottos.get(0), manualLottos.get(1))
                .allMatch(lotto -> lotto.getLottoNumbers().size() == 6);
    }

    @Test
    @DisplayName("수동 및 자동 로또 포함 총 구매 개수를 검증한다.")
    void should_GenerateLottos() {
        List<Lotto> lottos = lottoMachine.purchaseTickets(5000, manualLottos);
        assertThat(lottos).hasSize(5);
    }
}
