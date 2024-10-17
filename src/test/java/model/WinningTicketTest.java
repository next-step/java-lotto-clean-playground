package model;

import lotto.model.WinningTicket;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningTicketTest {
    @Test
    public void 공백이_일정한_당첨번호가_배열로_변환_되는_지(){
        //Given
        List<Integer> tmp = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> successList = new ArrayList<>();
        WinningTicket winningTicket = new WinningTicket(successList);
        String successNumStr = "1, 2, 3, 4, 5, 6";

        //When
        winningTicket.changeNumStrToArr(successNumStr);

        //Then
        Assertions.assertThat(tmp).isEqualTo(successList);
    }

    @Test
    public void 공백이_일정하지_않은_당첨번호가_배열로_변환_되는_지(){
        //Given
        List<Integer> tmp = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> successList = new ArrayList<>();
        WinningTicket winningTicket = new WinningTicket(successList);
        String successNumStr = "1,2, 3,4,5, 6";

        //When
        winningTicket.changeNumStrToArr(successNumStr);

        //Then
        Assertions.assertThat(tmp).isEqualTo(successList);
    }
}
