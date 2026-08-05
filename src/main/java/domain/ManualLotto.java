package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManualLotto {

    public static List<Lotto> generateManualLotto(int manualLottoCount, List<String> values) {
        if(manualLottoCount < 0) {
            throw new IllegalArgumentException("0 이상의 값을 받아야 합니다.");
        }

        List<Lotto> manualLotto = new ArrayList<>();
        for(int i = 0; i < manualLottoCount; i++) {
            manualLotto.add(convertStringToLottoNumber(splitString(values.get(i))));
        }

        return manualLotto;
    }

    private static String[] splitString(String input) {
        String[] manualLottoNumbers = input.split(",");

        manualLottoNumbers = Arrays.stream(manualLottoNumbers)
                .map(String::trim).toArray(String[]:: new); // 공백 처리

        return manualLottoNumbers;
    }


    private static Lotto convertStringToLottoNumber(String[] manualLottoNumbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();

        try {
            for (int i = 0; i < manualLottoNumbers.length; i++) {
                int number = Integer.parseInt(manualLottoNumbers[i]);

                lottoNumbers.add(new LottoNumber(number));
            }
        }

        catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자로 입력해주세요.");
        }

        return new Lotto(lottoNumbers);
    }
}
