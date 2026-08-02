package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoTickets {
    ArrayList<Lotto> lottoArrayList = new ArrayList<>();

    public void addUserSelectedLottos(List<String> userSelectedNumbersInput) {
        for (String numbersString : userSelectedNumbersInput) {
            List<Integer> numbers = Arrays.stream(numbersString.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            lottoArrayList.add(new Lotto(numbers));
        }
    }

    public void addAutoLottos(int autoCount) {
        for (int i = 0; i < autoCount; i++) {
            lottoArrayList.add(new Lotto());
        }
    }

    public TreeSet<Integer> getLottoTreeSet(int lottoTicketNumber){
        return new TreeSet<>(lottoArrayList.get(lottoTicketNumber).getRandomNumberSet());
    }

    public int getSize() {
        return lottoArrayList.size();
    }
}
