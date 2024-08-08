package domain.lotto.generator;

import domain.lotto.LottoTicket;

public interface LottoGenerator {
    
    LottoTicket issue();
}
