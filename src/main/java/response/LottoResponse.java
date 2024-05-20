package response;

import java.util.ArrayList;
import java.util.List;

import domain.LottoNumber;

public class LottoResponse {

	private final List<Integer> lottoResponse = new ArrayList<>();

	public LottoResponse(List<LottoNumber> lotto) {
		lotto.forEach((lottoNumber) -> lottoResponse.add(lottoNumber.getLottoNumber()));
	}

	public List<Integer> getLottoResponse() {
		return lottoResponse;
	}
}
