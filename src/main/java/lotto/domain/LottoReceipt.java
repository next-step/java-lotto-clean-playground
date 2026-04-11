package lotto.domain;

import java.util.List;

public record LottoReceipt(List<Lotto> lottoRows, int totalPrice) {
}
