package view;

import domain.Lotto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    public void outputLottoTickets(Lotto lotto) {
        System.out.println("\n" + lotto.getLottoCount() + "개를 구매했습니다.");

        lotto.getLottoNumberTickets().forEach(System.out::println);
    }
}
