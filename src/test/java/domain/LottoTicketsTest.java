package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LottoTickets 클래스")
class LottoTicketsTest {

    private LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        lottoTickets = new LottoTickets();
    }

    @Nested
    @DisplayName("addUserSelectedLottos 메소드는")
    class AddUserSelectedLottos {

        @Test
        @DisplayName("이미 생성된 로또 목록을 그대로 추가한다.")
        void shouldAddUserSelectedLottos() {
            List<Lotto> lottos = List.of(
                    new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                    new Lotto(List.of(7, 8, 9, 10, 11, 12))
            );

            lottoTickets.addUserSelectedLottos(lottos);

            assertThat(lottoTickets.getSize()).isEqualTo(2);
            assertThat(lottoTickets.getTicketNumbers(0)).containsExactly(1, 2, 3, 4, 5, 6);
            assertThat(lottoTickets.getTicketNumbers(1)).containsExactly(7, 8, 9, 10, 11, 12);
        }
    }

    @Nested
    @DisplayName("addAutoLottos 메소드는")
    class AddAutoLottos {

        @Test
        @DisplayName("주어진 개수만큼 자동 로또를 생성하고 추가한다.")
        void shouldAddAutoLottos() {
            int autoCount = 3;

            lottoTickets.addAutoLottos(autoCount);

            assertThat(lottoTickets.getSize()).isEqualTo(3);

            for (int i = 0; i < autoCount; i++) {
                assertThat(lottoTickets.getTicketNumbers(i)).hasSize(Lotto.LOTTO_NUMBER_COUNT);
            }
        }
    }

    @Nested
    @DisplayName("getTicketNumbers 메소드는")
    class GetTicketNumbers {

        @Test
        @DisplayName("지정된 인덱스의 로또 번호 Set을 반환한다.")
        void shouldReturnCorrectLottoSet() {
            lottoTickets.addAutoLottos(1);
            lottoTickets.addUserSelectedLottos(List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))));

            TreeSet<Integer> manualLottoSet = lottoTickets.getTicketNumbers(1);

            assertThat(manualLottoSet).containsExactly(1, 2, 3, 4, 5, 6);
        }
    }
}
