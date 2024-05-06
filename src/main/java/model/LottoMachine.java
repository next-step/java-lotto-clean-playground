package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import exception.BuyDirectNumber;

public class LottoMachine {

    private static List<Integer> allLottoNumber = new ArrayList<>();

    private List<Lotto> directNumbers;
    private List<Lotto> autoNumbers;

    static {

        for (int i = 1; i <= 45; i++) {
            allLottoNumber.add(i);
        }

    }

    public LottoMachine() {
        directNumbers = new ArrayList<>();
        autoNumbers = new ArrayList<>();
    }

    public void makeDirectNumber(List<String> directNumbersTexts) {

        for (int i = 0; i < directNumbersTexts.size(); i++) {
            BuyDirectNumber directNumber = BuyDirectNumber.from(directNumbersTexts.get(i));

            directNumbers.add(new Lotto(directNumber.value()));
        }
    }

    public void makeAutoNumber(final int autoCount) {

        for (int i = 0; i < autoCount; i++) {
            Collections.shuffle(allLottoNumber);

            autoNumbers.add(subList(0, 6));

            Collections.sort(autoNumbers.get(i).getLottoNumber());
        }
    }

    public static Lotto subList(int start, int end) {

        List<Integer> lottoNumber = allLottoNumber.stream()
                .skip(start)
                .limit(end + 1)
                .collect(Collectors.toList());

        return new Lotto(lottoNumber);
    }

    public List<Lotto> getDirectNumbers() {
        return directNumbers;
    }

    public List<Lotto> getAutoNumbers() {
        return autoNumbers;
    }
}
