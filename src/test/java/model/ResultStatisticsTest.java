package model;

import lotto.model.LottoNums;
import lotto.model.LottoTicket;
import lotto.model.LottoTickets;
import lotto.model.ResultStatistics;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ResultStatisticsTest {
    @Test
    void 당첨티켓과_티켓_일치율_계산() {
        //Given
        SoftAssertions softly = new SoftAssertions();
        ResultStatistics resultStatistics = new ResultStatistics();
        resultStatistics.statisticsInit();
        Map<Integer, Integer> statistics = resultStatistics.getStatistics();

        List<Integer> ticket1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> ticket2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7));
        List<Integer> successTicket = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall=7;

        //When
        resultStatistics.ticketMatchCount(ticket1,successTicket,bonusBall);
        resultStatistics.ticketMatchCount(ticket2,successTicket,bonusBall);
        int matchCount1= statistics.get(6);
        int matchCount2= statistics.get(7);
        softly.assertThat(matchCount1).isEqualTo(1);
        softly.assertThat(matchCount2).isEqualTo(1);

        //Then
        softly.assertAll();
    }
}
