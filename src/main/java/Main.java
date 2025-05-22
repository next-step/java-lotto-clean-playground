import domain.UserLottos;
import manager.LottoManager;

public class Main {
    public static void main(String[] args) {
        UserLottos userLottos = LottoManager.IssuingLottoTickets();
        LottoManager.calculateAndShowWinningStatistics(userLottos);
    }
}
