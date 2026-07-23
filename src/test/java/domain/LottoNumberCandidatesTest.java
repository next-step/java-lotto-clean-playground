package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberCandidatesTest {

    @Test
    @DisplayName("1부터 45까지의 후보 번호를 생성한다")
    void createCandidateNumbersFromOneToFortyFive() {
        List<Integer> candidateNumbers = LottoNumberCandidates.createAll();

        assertThat(candidateNumbers).hasSize(45);
        assertThat(candidateNumbers).contains(1, 45);
    }
}
