package dto;

import model.LottoNumber;
import model.LottoNumbers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbersDto {

    private final List<String> stringLottoNumbers;

    public LottoNumbersDto(List<String> stringLottoNumbers) {
        this.stringLottoNumbers = List.copyOf(stringLottoNumbers);
    }

    public LottoNumbers toLottoNumbers() {
        Set<LottoNumber> lottoNumbers = stringLottoNumbers.stream()
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toUnmodifiableSet());

        return new LottoNumbers(lottoNumbers);
    }

}
