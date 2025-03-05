package view;

import dto.LottoNumbersDto;

import java.util.*;

public class LottoInputView implements LottoView {

    private static final String INPUT_SEPARATOR = ", ";

    private final Scanner scanner = new Scanner(System.in);

    private static final LottoInputView lottoInputView = new LottoInputView();

    private LottoInputView() {
    }

    public static LottoInputView getInstance() {
        return lottoInputView;
    }

    public int getPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmountInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return purchaseAmountInput;
    }

    public int getManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manualLottoAmountInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return manualLottoAmountInput;
    }

    public List<LottoNumbersDto> getManualLottoNumbers(int manualLottoAmount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요");

        List<LottoNumbersDto> lottoNumbersDtos = new ArrayList<>();
        for (int i = 0; i < manualLottoAmount; i++) {
            List<String> manualLottoNumbersInput = scanNextLineAsList();
            lottoNumbersDtos.add(new LottoNumbersDto(manualLottoNumbersInput));
        }

        return Collections.unmodifiableList(lottoNumbersDtos);
    }

    public LottoNumbersDto getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<String> winningLottoNumbersInput = scanNextLineAsList();
        LottoNumbersDto winningLottoNumbersDto = new LottoNumbersDto(winningLottoNumbersInput);

        printEmptyLine();

        return winningLottoNumbersDto;
    }

    public int getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusBallInput = Integer.parseInt(scanner.nextLine());

        printEmptyLine();

        return bonusBallInput;
    }

    private List<String> scanNextLineAsList() {
        return Arrays.asList(scanner.nextLine().split(INPUT_SEPARATOR));
    }

}
