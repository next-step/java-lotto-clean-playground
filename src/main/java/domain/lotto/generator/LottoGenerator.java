package domain.lotto.generator;

import domain.lotto.IssuanceType;
import domain.lotto.LottoTicket;

public interface LottoGenerator {

    boolean supports(IssuanceType issuanceType);

    LottoTicket issue();
}
