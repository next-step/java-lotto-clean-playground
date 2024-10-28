package model;

import lotto.model.CalculateNumOfTickets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculateNumOfTicketsTest {
    @ParameterizedTest
    @ValueSource(ints={10000,20000,14000})
    void 티켓_개수를_잘_정하는_지_확인(int purchasePrice){
        //Given
        CalculateNumOfTickets calculateNumOfTickets = new CalculateNumOfTickets();

        //When
        calculateNumOfTickets.settingNumOfTickets(purchasePrice);
        int numOfTickets =calculateNumOfTickets.getNumOfTickets();

//        //Then
        if(purchasePrice==10000) Assertions.assertThat(numOfTickets).isEqualTo(10);
        else if(purchasePrice==20000) Assertions.assertThat(numOfTickets).isEqualTo(20);
        else if(purchasePrice==14000) Assertions.assertThat(numOfTickets).isEqualTo(14);
    }
}
