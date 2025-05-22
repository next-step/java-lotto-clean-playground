package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.LottoFactory;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLottosTest {
    private static UserLottos userLottos;
    private static WinningNumbers winningNumbers;

    @BeforeAll
    static void setUserLottos() {
        Amount amount = new Amount(6000);

        List<List<Integer>> manualNumberLists = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(2, 3, 4, 5, 6, 7)
        );

        List<List<Integer>> autoNumberLists = List.of(
                List.of(3, 4, 5, 6, 7, 8),
                List.of(4, 5, 6, 7, 8, 9),
                List.of(5, 6, 7, 8, 9, 10),
                List.of(6, 7, 8, 9, 10, 11)
        );

        List<Lotto> manualLottos = manualNumberLists.stream()
                .map(LottoFactory::generateManualLotto)
                .toList();

        List<Lotto> autoLottos = autoNumberLists.stream()
                .map(LottoFactory::generateManualLotto)
                .toList();


        userLottos = UserLottos.Builder.builder()
                .manualLottos(manualLottos)
                .autoLottos(autoLottos)
                .purchaseAmount(amount).build();

        winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    @DisplayName("userLottos의 getWinningResult() 메서드는, 유저가 소유한 로또의 당첨 결과를 반환한다.")
    void whenGivenFixedNumbersLotto_thenGetCorrespondingWinningResult() {
        Map<LottoRank, Integer> expectedResult = Map.of(
                LottoRank.FIRST, 1,
                LottoRank.SECOND, 1,
                LottoRank.FOURTH, 1,
                LottoRank.FIFTH, 1,
                LottoRank.NONE, 2
        );

        assertThat(userLottos.getWinningResult(winningNumbers).getValue()).isEqualTo(expectedResult);
    }


}
