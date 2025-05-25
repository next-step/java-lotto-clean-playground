package domain;

import java.util.List;

public class Purchase {
    private final int totalCount;
    private final int manualCount;
    private final List<Lotto> manualLottos;

    public Purchase(int totalCount, int manualCount, List<Lotto> manualLottos) {
        validate(totalCount, manualCount, manualLottos);
        this.totalCount = totalCount;
        this.manualCount = manualCount;
        this.manualLottos = manualLottos;
    }

    private void validate(int totalCount, int manualCount, List<Lotto> manualLottos) {
        if (totalCount < 0 || manualCount < 0) {
            throw new IllegalArgumentException("구매 개수는 0 이상이어야 합니다.");
        }

        if (manualCount > totalCount) {
            throw new IllegalArgumentException("수동 구매 개수가 전체 구매 개수를 초과할 수 없습니다.");
        }

    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }
}
