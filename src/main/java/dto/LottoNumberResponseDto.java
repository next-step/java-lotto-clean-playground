package dto;

import model.Lotto;

import java.util.List;

public class LottoNumberResponseDto {

    private final List<Integer> sortedLottoNumbers;

    public LottoNumberResponseDto(Lotto lotto) {
        this.sortedLottoNumbers = lotto.getSortedLottoNumbers();
    }

    public List<Integer> getSortedLottoNumbers() {
        return sortedLottoNumbers;
    }

}