package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        // 당첨 번호: 1, 2, 3, 4, 5, 6 / 보너스 번호: 7
        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)
        ));
        LottoNumber bonusNumber = new LottoNumber(7);

        winningLotto = new WinningLotto(lotto, bonusNumber);
    }

    @Test
    @DisplayName("Prize.NONE이 아닌 당첨 개수만 기록되어야 한다.")
    void onlyWinningPrizes_ShouldBeRecorded() {
        // Given (다양한 로또 번호 리스트)
        List<Lotto> lottoList = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList()),  // 1등 (6개 일치)
                new Lotto(List.of(1, 2, 3, 4, 5, 7).stream().map(LottoNumber::new).toList()),  // 2등 (5개 + 보너스)
                new Lotto(List.of(1, 2, 3, 4, 5, 8).stream().map(LottoNumber::new).toList()),  // 3등 (5개)
                new Lotto(List.of(1, 2, 3, 4, 10, 11).stream().map(LottoNumber::new).toList()), // 4등 (4개)
                new Lotto(List.of(1, 2, 3, 20, 21, 22).stream().map(LottoNumber::new).toList()) // 5등 (3개)
        );

        // When
        LottoResult lottoResult = LottoResult.from(lottoList, winningLotto);
        Map<Prize, Integer> matchCountMap = lottoResult.getMatchCountMap();

        // Then (Prize.NONE은 저장되지 않아야 함)
        assertThat(matchCountMap.containsKey(Prize.NONE)).isFalse();

        // 당첨 개수 검증
        assertThat(matchCountMap.getOrDefault(Prize.FIRST_PRIZE, 0)).isEqualTo(1);  // 1등 1개
        assertThat(matchCountMap.getOrDefault(Prize.SECOND_PRIZE, 0)).isEqualTo(1); // 2등 1개
        assertThat(matchCountMap.getOrDefault(Prize.THIRD_PRIZE, 0)).isEqualTo(1);  // 3등 1개
        assertThat(matchCountMap.getOrDefault(Prize.FOURTH_PRIZE, 0)).isEqualTo(1); // 4등 1개
        assertThat(matchCountMap.getOrDefault(Prize.FIFTH_PRIZE, 0)).isEqualTo(1);  // 5등 1개
    }

    @Test
    @DisplayName("로또 하나하나 보면서 당첨 여부가 올바르게 기록되어야 한다.")
    void eachLotto_ShouldBeRecordedCorrectly() {
        // Given (2개의 로또, 1등과 3등)
        List<Lotto> lottoList = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList()),  // 1등 (6개 일치)
                new Lotto(List.of(1, 2, 3, 4, 5, 8).stream().map(LottoNumber::new).toList())   // 3등 (5개)
        );

        // When
        LottoResult lottoResult = LottoResult.from(lottoList, winningLotto);
        Map<Prize, Integer> matchCountMap = lottoResult.getMatchCountMap();

        // Then
        assertThat(matchCountMap.getOrDefault(Prize.FIRST_PRIZE, 0)).isEqualTo(1);
        assertThat(matchCountMap.getOrDefault(Prize.THIRD_PRIZE, 0)).isEqualTo(1);
    }

    @Test
    @DisplayName("총 상금이 바르게 계산되어야 한다.")
    void totalPrize_ShouldBeCalculatedCorrectly() {
        // Given
        List<Lotto> lottoList = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6).stream().map(LottoNumber::new).toList()),  // 1등 (20억)
                new Lotto(List.of(1, 2, 3, 4, 5, 7).stream().map(LottoNumber::new).toList()),  // 2등 (3천만)
                new Lotto(List.of(1, 2, 3, 4, 5, 8).stream().map(LottoNumber::new).toList()),  // 3등 (150만)
                new Lotto(List.of(1, 2, 3, 4, 10, 11).stream().map(LottoNumber::new).toList()), // 4등 (5만)
                new Lotto(List.of(1, 2, 3, 20, 21, 22).stream().map(LottoNumber::new).toList()) // 5등 (5천)
        );

        // When
        LottoResult lottoResult = LottoResult.from(lottoList, winningLotto);
        long totalPrize = lottoResult.calculateTotalPrize();

        // Then
        long expectedTotalPrize =
                Prize.FIRST_PRIZE.getPrizeMoney() +   // 2,000,000,000원
                        Prize.SECOND_PRIZE.getPrizeMoney() +  // 30,000,000원
                        Prize.THIRD_PRIZE.getPrizeMoney() +   // 1,500,000원
                        Prize.FOURTH_PRIZE.getPrizeMoney() +  // 50,000원
                        Prize.FIFTH_PRIZE.getPrizeMoney();    // 5,000원

        assertThat(totalPrize).isEqualTo(expectedTotalPrize);
    }
}
