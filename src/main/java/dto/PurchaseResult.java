package dto;

import domain.lotto.Lottos;
import domain.lotto.Money;


public record PurchaseResult(
        Money purchasePrice,
        Lottos lottos
) {
}
