package domain;

import enums.LottoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.*;

class LottoShopTest {

    LottoShop lottoShop;
    LottoMachine lottoMachine;
    List<Lotto> lottos;

    @BeforeEach
    void beforeEach() {
        List<List<Integer>> fixedNumbersList = List.of(
                new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)),
                new ArrayList<>(List.of(2, 3, 4, 5, 6, 7)),
                new ArrayList<>(List.of(3, 4, 5, 6, 7, 8))
        );
        lottoMachine = new LottoMachine(new FakeNumberGenerator(fixedNumbersList));
        lottoShop = new LottoShop(lottoMachine);

        lottos = new ArrayList<>();
        lottos.add(Lotto.from(List.of(4, 5, 6, 7, 8, 9), LottoType.AUTO));
        lottos.add(Lotto.from(List.of(5, 6, 7, 8, 9, 10), LottoType.AUTO));
        lottos.add(Lotto.from(List.of(6, 7, 8, 9, 10, 11), LottoType.AUTO));
    }

    @Test
    @DisplayName("수동로또개수는 로또개수보다 많으면 예외를 던진다")
    void 수동로또개수는_로또개수보다_많으면_예외를_던진다() {
        assertThatThrownBy(() -> lottoShop.purchaseLottos(Money.from(3000), LottoCount.from(4), lottos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("LottoShop이_수동로또_자동로또를_알맞게_반환한다")
    void LottoShop이_수동로또_자동로또를_알맞게_반환한다() {
        //given
        List<List<Integer>> fixedNumbersList = List.of(
                new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)),
                new ArrayList<>(List.of(2, 3, 4, 5, 6, 7)),
                new ArrayList<>(List.of(3, 4, 5, 6, 7, 8))
        );
        lottoMachine = new LottoMachine(new FakeNumberGenerator(fixedNumbersList));
        lottoShop = new LottoShop(lottoMachine);

        List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(Lotto.from(List.of(4, 5, 6, 7, 8, 9), LottoType.AUTO));
        manualLottos.add(Lotto.from(List.of(5, 6, 7, 8, 9, 10), LottoType.AUTO));

        //when
        Lottos purchasedLottos = lottoShop.purchaseLottos(Money.from(3000), LottoCount.from(2), manualLottos);
        List<Lotto> lottos = purchasedLottos.getLottos();
        // then
        assertThat(lottos)
                .hasSize(3) // 3개의 로또가 있어야 함
                .extracting(Lotto::getLottoNumbers) // Lotto 객체의 번호 리스트만 추출
                .containsExactly(
                        new TreeSet<>(List.of(LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6), LottoNumber.from(7), LottoNumber.from(8), LottoNumber.from(9))),
                        new TreeSet<>(List.of(LottoNumber.from(5), LottoNumber.from(6), LottoNumber.from(7), LottoNumber.from(8), LottoNumber.from(9), LottoNumber.from(10))),
                        new TreeSet<>(List.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3), LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6)))
                );
    }

    @Test
    @DisplayName("수동 로또 개수가 0이면 전부 자동 로또로 구매된다")
    void 수동_로또_개수가_0이면_전부_자동_로또로_구매된다() {
        // given
        List<Lotto> manualLottos = new ArrayList<>();
        Money money = Money.from(3000);
        LottoCount manualCount = LottoCount.from(0);

        // when
        Lottos result = lottoShop.purchaseLottos(money, manualCount, manualLottos);

        // then
        assertThat(result.getManualCount().getLottoCount()).isEqualTo(0);
        assertThat(result.getAutoCount().getLottoCount()).isEqualTo(3);
        assertThat(result.getLottoCount().getLottoCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("로또 가격보다 적은 금액을 입력하면 예외가 발생한다")
    void 로또_가격보다_적은_금액을_입력하면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoShop.purchaseLottos(Money.from(500), LottoCount.from(0), List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동 로또 개수와 수동 번호 리스트 개수가 다르면 예외 발생")
    void 수동_로또_개수와_수동_번호_리스트_개수가_다르면_예외_발생() {
        List<Lotto> manualLottos = List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6), LottoType.MANUAL)
        );
        assertThatThrownBy(() -> lottoShop.purchaseLottos(Money.from(3000), LottoCount.from(2), manualLottos))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
