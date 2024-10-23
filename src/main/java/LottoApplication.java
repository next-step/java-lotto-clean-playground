import controller.LottoController;

public class LottoApplication {
    public static void main(String[] args) {
        final LottoController controller = new LottoController();
        controller.howManyOfLottos();
        controller.lottoGenerator();
        controller.winningNumberAndBonusNumber();
        controller.findWinningLotto();
    }
}
