package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import config.ResultType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

class WinningLottoTest {

    private static Numbers numbers;
    private static int bonusNumber;

    @BeforeEach
    void before() {
        numbers = new Numbers(List.of(1, 2, 3, 4, 5, 6));
        bonusNumber = 7;
    }

    @Test
    @DisplayName("당첨 로또의 보너스 번호는 로또 번호와 겹쳐선 안된다.")
    void validateBonusNumberWithNumbers() {
        bonusNumber = 6;
        assertThatThrownBy(() -> new WinningLotto(numbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보너스 볼은 로또 번호와 겹쳐선 안됩니다.");
    }

    @Test
    @DisplayName("당첨 로또는 당첨번호와 로또번호를 비교하여 로또 스탯을 업데이트할 수 있어야 한다.")
    void setLottoResultTest() {
        final WinningLotto winningLotto = new WinningLotto(numbers, bonusNumber);
        final List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(numbers));
        LottoStat lottoStat = new LottoStat();

        winningLotto.setLottoResult(lottos, lottoStat);
        final Map<ResultType, Integer> statics = lottoStat.getStatics();

        assertThat(statics.get(ResultType.FIRST)).isEqualTo(1);
        assertThat(statics.get(ResultType.SECOND)).isEqualTo(0);
    }

}
