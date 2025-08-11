package util;

import java.util.ArrayList;
import vo.Lotto;
import vo.LottoNumber;

public class LottoInputParser {

    // 구입 금액 문자열을 정수로 파싱하는 메서드
    public static int parseMoney(String input) {
        LottoValidator.validateMoney(input);
        return Integer.parseInt(input);
    }

    // 당첨 번호 문자열을 Lotto 객체로 파싱하는 메서드
    public static Lotto parseWinningNumbers(String input) {
        LottoValidator.validateWinningNumbersInput(input);

        String[] numberStrings = input.split(",");
        ArrayList<LottoNumber> winningNumbers = new ArrayList<>();
        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString.trim());
            winningNumbers.add(new LottoNumber(number));
        }

        return new Lotto(winningNumbers);
    }

    // 보너스 번호 문자열을 파싱하는 메서드
    public static LottoNumber parseBonusBall(String input) {
        LottoValidator.validateBonusNumber(input);
        int number = Integer.parseInt(input.trim());
        return new LottoNumber(number);
    }

    // 수동 로또 개수 문자열을 정수로 파싱하는 메서드
    public static int parseManualLottoCount(String input) {
        LottoValidator.validateManualLottoCount(input);
        return Integer.parseInt(input);
    }

    // 수동 로또 번호 문자열 리스트를 Lotto 객체 리스트로 파싱하는 메서드
    public static ArrayList<Lotto> parseManualLottos(ArrayList<String> input) {
        ArrayList<Lotto> manualLottos = new ArrayList<>();
        for (String manualLottoInput : input) {
            manualLottos.add(parseManualLotto(manualLottoInput));
        }
        return manualLottos;
    }

    // 단일 수동 로또 번호 문자열을 Lotto 객체로 파싱하는 메서드
    public static Lotto parseManualLotto(String input) {
        LottoValidator.validateManualLottoInput(input);

        String[] numberStrings = input.split(",");
        ArrayList<LottoNumber> manualLottos = new ArrayList<>();
        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString.trim());
            manualLottos.add(new LottoNumber(number));
        }

        return new Lotto(manualLottos);
    }
}
