package service;

import model.LottoNumber;
import model.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class AutomaticLottoNumberGeneratorTest {
    @Test
    @DisplayName("자동 번호 생성기에서 생성된 로또 티켓은 6개의 번호를 가져야 한다.")
    void testGenerateOneAutomaticTicket_Has6Numbers(){
        LottoTicket lottoTicket = AutomaticLottoNumberGenerator.generateAutomaticNumbers();

        assertThat(lottoTicket.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 번호 생성기에서 생성된 로또 번호는 중복되지 않아야 한다.")
    void testGenerateOneAutomaticTicket_NotDuplicate(){
        LottoTicket lottoTicket = AutomaticLottoNumberGenerator.generateAutomaticNumbers();
        Set<LottoNumber> uniqueNumbers = new HashSet<>(lottoTicket.getNumbers());

        assertThat(uniqueNumbers).hasSize(6);
    }

    @Test
    @DisplayName("자동 번호 생성기에서 생성된 로또 번호는 1 이상 45 이하의 범위여야 한다.")
    void testGenerateOneAutomaticTicket_RangeOf1To45(){
        LottoTicket lottoTicket = AutomaticLottoNumberGenerator.generateAutomaticNumbers();

        for (LottoNumber number : lottoTicket.getNumbers()) {
            assertThat(number.getNumber()).isGreaterThanOrEqualTo(1);
            assertThat(number.getNumber()).isLessThanOrEqualTo(45);
        }
    }

    @Test
    @DisplayName("자동 번호 생성기에서 생성된 로또 번호는 정렬된 상태여야 한다.")
    void testGenerateOneAutomaticTicket_SortedNumbers(){
        LottoTicket lottoTicket = AutomaticLottoNumberGenerator.generateAutomaticNumbers();

        List<LottoNumber> sortedNumbers = new ArrayList<>(lottoTicket.getNumbers());
        Collections.sort(sortedNumbers);

        assertThat(lottoTicket.getNumbers()).isEqualTo(sortedNumbers);
    }
}
