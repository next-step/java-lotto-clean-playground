package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;


    @Test
    @DisplayName("로또숫자 생성")
    public void lottoTest() {
        //given
        int expectedLottoTicketCount = 12;

        List<Integer> expectedlottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            expectedlottoNumberList.add(i);
        }

        List<List<Integer>> expectedlottoNumberTickets = new ArrayList<>();

        List<Integer> lottoTicket;

        for(int i = 0;i < expectedLottoTicketCount; i++) {
            Collections.shuffle(expectedlottoNumberList);

            lottoTicket = expectedlottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION);
            Collections.sort(lottoTicket);
            expectedlottoNumberTickets.add(lottoTicket.stream().toList());
        }

        //when
        Lotto lotto = new Lotto(expectedLottoTicketCount, expectedlottoNumberList, expectedlottoNumberTickets);

        //then
        assertAll(
                () -> assertThat(expectedLottoTicketCount).isEqualTo(lotto.getLottoCount()),
                () -> assertThat(expectedlottoNumberList).isEqualTo(lotto.getLottoNumberList()),
                () -> assertThat(expectedlottoNumberTickets).isEqualTo(lotto.getLottoNumberTickets())
        );
    }
}
