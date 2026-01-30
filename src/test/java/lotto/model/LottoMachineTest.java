package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.model.LottoMachine;
import lotto.domain.model.Lottos;
import lotto.domain.model.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("지정된 개수만큼 로또를 발급한다.")
    void issue_Lottos() {
        // given
        int issueCount = 5;
        LottoMachine lottoMachine = new LottoMachine(() ->
            Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::valueOf)
                .toList()
        );

        // when
        Lottos issuedLottos = lottoMachine.issue(issueCount);

        // then
        assertThat(issuedLottos.size()).isEqualTo(issueCount);
        assertThat(issuedLottos.getValues().get(0).getNumbers())
            .extracting(LottoNumber::getNumber)
            .containsExactly(1, 2, 3, 4, 5, 6);
    }

}
