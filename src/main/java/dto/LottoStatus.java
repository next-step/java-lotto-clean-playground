package dto;

import domain.LottoNumber;

import java.util.List;

public record LottoStatus(List<LottoNumber> lottoNumbers) {
    public LottoStatus {
        lottoNumbers = List.copyOf(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
