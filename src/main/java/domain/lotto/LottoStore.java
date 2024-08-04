package domain.lotto;

import domain.common.Money;
import domain.lotto.generator.LottoGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoStore {

    private final Map<IssuanceType, LottoGenerator> lottoGenerators;

    public LottoStore(Map<IssuanceType, LottoGenerator> lottoGenerators) {
        this.lottoGenerators = lottoGenerators;
    }

    public List<LottoTicket> sellLottos(int totalCount, ManualCount manualCount) {

        int autoCount = totalCount - manualCount.getCount();

        List<LottoTicket> lottoTickets = new ArrayList<>();

        manualIssue(manualCount.getCount(), lottoTickets);
        autoIssue(autoCount, lottoTickets);

        return lottoTickets;
    }

    private void manualIssue(int manualCount, List<LottoTicket> lottoTickets) {
        if (manualCount <= 0) {
            return;
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualCount; i++) {
            final LottoGenerator lottoGenerator = lottoGenerators.get(IssuanceType.MANUAL);
            final LottoTicket issuedTicket = lottoGenerator.issue();
            lottoTickets.add(issuedTicket);
        }
    }

    private void autoIssue(int autoCount, List<LottoTicket> lottoTickets) {
        for (int i = 0; i < autoCount; i++) {
            final LottoGenerator lottoGenerator = lottoGenerators.get(IssuanceType.AUTO);
            LottoTicket issuedTicket = lottoGenerator.issue();
            lottoTickets.add(issuedTicket);
        }
    }
}
