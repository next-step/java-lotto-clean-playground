package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static List<Integer> autoLottoGenerator() {
        final int RANDOM_VALUE_MAX = 45;
        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i <= RANDOM_VALUE_MAX; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);
        List<Integer> lottoNumber = numList.subList(0, 6);
        Collections.sort(lottoNumber);
        return lottoNumber;
    }

    public static List<List<Integer>> manualLottoGenerator(List<String> manualLottoInputs) {
        List<List<Integer>> manualLottoCollection = new ArrayList<>();

        for (String input : manualLottoInputs) {
            List<Integer> manualLotto = new ArrayList<>();
            StringBuilder numberBuffer = new StringBuilder();

            for (char ch : input.toCharArray()) {
                if (Character.isDigit(ch)) {
                    numberBuffer.append(ch);
                }
                if (ch == ',' || ch == ' ') {
                    if (!numberBuffer.isEmpty()) {
                        manualLotto.add(Integer.parseInt(numberBuffer.toString()));
                        numberBuffer.setLength(0);
                    }
                }
            }
            if (!numberBuffer.isEmpty()) {
                manualLotto.add(Integer.parseInt(numberBuffer.toString()));
            }
            manualLottoCollection.add(manualLotto);
        }
        return manualLottoCollection;
    }

    public static List<List<Integer>> multipleLottoGenerator(int numberOfAutoLottos) {
        List<List<Integer>> lottoCollection = new ArrayList<>();

        for (int i = 0; i < numberOfAutoLottos; i++) {
            lottoCollection.add(autoLottoGenerator());
        }
        return lottoCollection;
    }
}
