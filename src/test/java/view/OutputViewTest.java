package view;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    @Test
    @DisplayName("로또 생성 확인 메서드")
    public void a() {
        // given
        Random random = new Random();
        int money = 10000;

        //when
        Lotto lotto = new Lotto(10000);

        //then
        new OutputView().outputLottoTickets(lotto);
    }
}
