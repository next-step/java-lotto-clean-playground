import domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ServiceTest {

    // Purchase 테스트
    @DisplayName("정상적인 구매 정보로 Purchase 객체가 생성된다")
    @Test
    void createPurchase_ValidInfo_Success() {
        List<Lotto> manualLottos = Arrays.asList(
                new Lotto(Arrays.asList(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );

        assertThatCode(() -> new Purchase(5, 1, manualLottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("수동 구매 개수가 전체 구매 개수를 초과할 때 예외가 발생한다")
    @Test
    void createPurchase_ManualCountExceedsTotal_ThrowsException() {
        List<Lotto> manualLottos = Arrays.asList();

        assertThatThrownBy(() -> new Purchase(3, 5, manualLottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수동 구매 개수가 전체 구매 개수를 초과할 수 없습니다.");
    }

    @DisplayName("자동 구매 개수를 정확히 계산한다")
    @Test
    void purchase_GetAutoCount_Success() {
        List<Lotto> manualLottos = Arrays.asList();
        Purchase purchase = new Purchase(5, 2, Arrays.asList());

        assertThat(purchase.getAutoCount()).isEqualTo(3);
    }

    // LottoResult 테스트
    @DisplayName("결과를 추가하고 개수를 정확히 조회한다")
    @Test
    void lottoResult_AddResult_Success() {
        LottoResult result = new LottoResult();

        result.addResult(Rank.FIRST);
        result.addResult(Rank.FIFTH);
        result.addResult(Rank.FIFTH);

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.getCountByRank(Rank.NONE)).isEqualTo(0);
    }

    @DisplayName("총 상금을 정확히 계산한다")
    @Test
    void lottoResult_CalculateTotalPrize_Success() {
        LottoResult result = new LottoResult();

        result.addResult(Rank.FIRST);    // 2,000,000,000원
        result.addResult(Rank.FIFTH);    // 5,000원
        result.addResult(Rank.FIFTH);    // 5,000원

        long expectedTotal = 2_000_000_000L + 5_000L + 5_000L;
        assertThat(result.calculateTotalPrize()).isEqualTo(expectedTotal);
    }

    //LottoGenerator 테스트
    @DisplayName("로또를 1개 생성한다")
    @Test
    void lottoGenerator_GenerateSingle_Success() {
        LottoGenerator generator = new LottoGenerator();

        Lotto lotto = generator.generate();

        assertThat(lotto).isNotNull();
        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @DisplayName("로또를 여러 개 생성한다")
    @Test
    void lottoGenerator_GenerateMultiple_Success() {
        LottoGenerator generator = new LottoGenerator();
        int count = 5;

        List<Lotto> lottos = generator.generate(count);

        assertThat(lottos).hasSize(count);
        lottos.forEach(lotto -> {
            assertThat(lotto).isNotNull();
            assertThat(lotto.getNumbers()).hasSize(6);
        });
    }

    @DisplayName("생성된 로또의 번호는 1-45 범위 내에 있다")
    @Test
    void lottoGenerator_ValidRange_Success() {
        LottoGenerator generator = new LottoGenerator();

        Lotto lotto = generator.generate();

        lotto.getNumberValues().forEach(number -> {
            assertThat(number).isBetween(1, 45);
        });
    }

    @DisplayName("생성된 로또는 중복 번호가 없다")
    @Test
    void lottoGenerator_NoDuplicate_Success() {
        LottoGenerator generator = new LottoGenerator();

        Lotto lotto = generator.generate();
        List<Integer> numbers = lotto.getNumberValues();

        assertThat(numbers.stream().distinct().count()).isEqualTo(6);
    }

    // LottoService 테스트
    @DisplayName("구매 정보에 따라 로또 티켓을 발행한다")
    @Test
    void lottoService_PurchaseLottos_Success() {
        LottoService service = new LottoService();
        List<Lotto> manualLottos = Arrays.asList(
                new Lotto(Arrays.asList(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        Purchase purchase = new Purchase(5, 1, manualLottos);

        LottoTickets tickets = service.purchaseLottos(purchase);

        assertThat(tickets.getTotalCount()).isEqualTo(5);
        assertThat(tickets.getManualCount()).isEqualTo(1);
        assertThat(tickets.getAutoCount()).isEqualTo(4);
    }

    @DisplayName("로또 결과를 정확히 계산한다")
    @Test
    void lottoService_CalculateResult_Success() {
        LottoService service = new LottoService();

        List<LottoNumber> winningNumbers = Arrays.asList(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        );
        WinningLotto winningLotto = new WinningLotto(winningNumbers, new LottoNumber(7));

        List<Lotto> manualLottos = Arrays.asList(
                new Lotto(Arrays.asList(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        LottoTickets tickets = new LottoTickets(manualLottos, Arrays.asList());

        LottoResult result = service.calculateResult(tickets, winningLotto);

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
    }

    // LottoTickets 테스트
    @DisplayName("로또 티켓이 올바르게 구성된다")
    @Test
    void lottoTickets_Creation_Success() {
        List<Lotto> manualLottos = Arrays.asList(
                new Lotto(Arrays.asList(
                        new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                        new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
                ))
        );
        List<Lotto> autoLottos = Arrays.asList(
                new Lotto(Arrays.asList(
                        new LottoNumber(7), new LottoNumber(8), new LottoNumber(9),
                        new LottoNumber(10), new LottoNumber(11), new LottoNumber(12)
                )),
                new Lotto(Arrays.asList(
                        new LottoNumber(13), new LottoNumber(14), new LottoNumber(15),
                        new LottoNumber(16), new LottoNumber(17), new LottoNumber(18)
                ))
        );

        LottoTickets tickets = new LottoTickets(manualLottos, autoLottos);

        assertThat(tickets.getManualCount()).isEqualTo(1);
        assertThat(tickets.getAutoCount()).isEqualTo(2);
        assertThat(tickets.getTotalCount()).isEqualTo(3);
        assertThat(tickets.getTickets()).hasSize(3);
    }
}
