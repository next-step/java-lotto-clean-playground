package dto;

import java.util.List;

public record LottoStatus(List<Integer> lottoNumbers) {
    public LottoStatus {
        lottoNumbers = List.copyOf(lottoNumbers);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
