package domain.lottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import view.InputView;

public class LottoNumberService {
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;
    private final InputView inputView = new InputView();

    public List<Integer> generateLottoNumberList() {
        List<Integer> lottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumberList.add(i);
        }

        return lottoNumberList;
    }

    public LottoNumber generateLottoNumber() {
        List<Integer> lottoNumberList = generateLottoNumberList();
        Collections.shuffle(lottoNumberList);
        return new LottoNumber(lottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION));
    }

    public LottoNumber generateManualLottoNumber() {
        List<Integer> ManualNumber = readManualNumbers();
        return new LottoNumber(ManualNumber);
    }

    public List<Integer> readManualNumbers() {
        List<Integer> ManualNumbers = inputView.readManualLottoNumber();
        validateDuplication(ManualNumbers);
        validateRange(ManualNumbers);
        return ManualNumbers;
    }

    public void validateDuplication(List<Integer> manualNumber) {
        long size = manualNumber.stream().distinct().count();
        if(size != LAST_LOTTO_POSITION) {
            throw new IllegalArgumentException("수동 로또 번호는 중복된 번호를 입력받을 수 없습니다.");
        }
    }

    public void validateRange(List<Integer> manualNumbers) {
        boolean invalidNumber = manualNumbers.stream()
                .anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER);

        if (invalidNumber) {
            throw new IllegalArgumentException("수동으로 선택한 숫자는 1부터 45까지의 값이어야 합니다.");
        }
    }
}
